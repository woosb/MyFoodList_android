package com.example.myfoodlist.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.common.StringUtil;
import com.example.myfoodlist.room.StoreData;
import com.example.myfoodlist.room.StoreDb;
import com.example.myfoodlist.room.StoreListAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddStoreDetailActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private static final int DEFAULT_GALLERY_REQUEST_CODE = 1;
    String currentImagePath = null;
    String imgName;
    Button btn_add_detail;

    EditText et_name, et_addr, et_score, et_memo;
    ImageView iv_picture;

    RecyclerView recyclerView;

    String title, addr, memo;
    int score;

    List<StoreData> storeDataList = new ArrayList<>();
    StoreDb database;
    StoreListAdapter adapter;

    double latitude;
    double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store_detail);

        //권한체크
        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("거부하셨습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).check();

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude",0);
        longitude = intent.getDoubleExtra("longitude",0);

        String getAddrFromLatLng = getAddress(new LatLng(latitude, longitude));

        String img_path = intent.getStringExtra("img_path");
        if(img_path != null){
            Log.d("img_path", img_path);
        }

        btn_add_detail = findViewById(R.id.btn_add_detail);
        et_name = findViewById(R.id.et_name);
        et_addr = findViewById(R.id.et_addr);
        et_score = findViewById(R.id.et_score);
        et_score.setFilters(new InputFilter[]{new InputFilterMinMax(1,100)});
        et_memo = findViewById(R.id.et_memo);
        iv_picture = findViewById(R.id.iv_picture);

        et_addr.setText(getAddrFromLatLng);

        database = StoreDb.getInstance(this);
        storeDataList = database.storeDataDao().getAll();
        adapter = new StoreListAdapter(AddStoreDetailActivity.this, storeDataList);

        // detail 조회 화면 따로 추가
        /*
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        recyclerView.setAdapter(adapter);
        */
        iv_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_menu1){
                            captureImage();
                        }else if (menuItem.getItemId() == R.id.action_menu2){
                            showGallery();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        btn_add_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = et_name.getText().toString();
                addr = et_addr.getText().toString();
                score = Integer.parseInt(et_score.getText().toString());
                memo = et_memo.getText().toString();

                StoreData storeData = new StoreData();
                storeData.setName(title);
                storeData.setAddress(addr);
                storeData.setScore(score);
                storeData.setMemo(memo);
                storeData.setThumbnail(imgName);
                storeData.setLatitude(latitude);
                storeData.setLongitude(longitude);
                storeData.setInsYmdHms(StringUtil.getDateTime());

                Log.e("storeData", storeData.toString());

                database.storeDataDao().insert(storeData);
                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());

                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());
                adapter.notifyDataSetChanged();

                Intent detailToMain = new Intent(AddStoreDetailActivity.this, MainActivity.class);
                startActivity(detailToMain);

            }
        });
    }

    public void captureImage(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;

            try {
                imageFile = getImageFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            // 파일이 null이 아니면
            if(imageFile != null){
                Uri imageUri= FileProvider.getUriForFile(this, "com.example.myfoodlist", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File getImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_" + timeStamp + "_";
        // 공용 폴더
        //File publicDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        // 어플 내부용 폴더 앱 이외에는 비공개
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // 캐시 폴더에 저장
        //File cacheDir = getCacheDir();
        File imageFile = File.createTempFile(imageName, ".jpg", storageDir);
        imgName = imageFile.getName();
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    public void showGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, DEFAULT_GALLERY_REQUEST_CODE);
    }

    //위도/경도로 주소 값 가져오기.
    private String getAddress(LatLng latLng){
        Geocoder geocoder = new Geocoder(AddStoreDetailActivity.this, Locale.KOREA);
        String addr = "";

        try{
            List<Address> fromLocation = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            addr = fromLocation.get(0).getAddressLine(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return addr;
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("requestCode", requestCode + "");

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File file = new File(currentImagePath);
            Bitmap rotateBitmap = null;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                if (bitmap != null) {
                    ExifInterface exif = new ExifInterface(currentImagePath);
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
                    rotateBitmap = rotateImg(bitmap, exifOrientationToDegrees(orientation));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 캡쳐한 사진을 imageView에 그려주는 부분
            iv_picture.setImageBitmap(rotateBitmap);

        }

        if(requestCode == DEFAULT_GALLERY_REQUEST_CODE){
            try(InputStream io = getContentResolver().openInputStream(data.getData())) {
                Bitmap img = BitmapFactory.decodeStream(io);
                iv_picture.setImageBitmap(img);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Bitmap rotateImg(Bitmap source, float orientation){
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    private int exifOrientationToDegrees(int exifOrientation){
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }
    };
}