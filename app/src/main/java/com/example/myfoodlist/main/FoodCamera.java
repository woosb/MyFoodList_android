package com.example.myfoodlist.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.example.myfoodlist.samples.MediaScanner;
import com.example.myfoodlist.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodCamera extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private static final int DEFAULT_GALLERY_REQUEST_CODE = 1;
    String currentImagePath = null;

    ImageView iv_result;
    Button btn_prev;
    Button btn_next;

    List photoList = new ArrayList();
    private MediaScanner mMediaScanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_camera);

        Button btn_capture = findViewById(R.id.btn_capture);
        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);

        iv_result = findViewById(R.id.iv_result);

        //loadImg();
//        //권한체크
//        TedPermission.create()
//                .setPermissionListener(permissionListener)
//                .setRationaleMessage("카메라 권한이 필요합니다.")
//                .setDeniedMessage("거부하셨습니다.")
//                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).check();

        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){showNext();}
        });

        btn_prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){showPrev();}
        });

        captureImage();
    }

    public void showNext(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, DEFAULT_GALLERY_REQUEST_CODE);
    }

    public void showPrev(){
        //사진파일 저장폴더
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String[] photoLists = storageDir.list();
        String name = photoLists[photoLists.length-1];
        File file = new File(storageDir+File.separator+name);
        Log.e("filePath", file.getPath());
        Uri uri = Uri.parse(file.getPath());
        iv_result.setImageURI(uri);
    }

    public void saveImg(){
        File saveDir = new File(getFilesDir()+"/Pictures");
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        Log.e("saveDir on save", saveDir.toString());

        String filename = "캡처파일" + ".jpg";
        File file = new File(saveDir, filename);

        try(FileOutputStream fos = new FileOutputStream(file)){
            BitmapDrawable bitmapDrawable = (BitmapDrawable)iv_result.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            //addPicToGallery(this, currentImagePath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadImg(){
        try{
            File saveDir = new File(getFilesDir() + "/capture");
            String filename = "캡쳐파일" + ".jpg";
            File file = new File(saveDir, filename);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            iv_result.setImageBitmap(bitmap);
        }catch(Exception e){
            e.printStackTrace();
        }
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
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
           iv_result.setImageBitmap(rotateBitmap);
        }

        if(requestCode == DEFAULT_GALLERY_REQUEST_CODE){
            try(InputStream io = getContentResolver().openInputStream(data.getData())) {
                Bitmap img = BitmapFactory.decodeStream(io);
                iv_result.setImageBitmap(img);
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

//    PermissionListener permissionListener = new PermissionListener() {
//        @Override
//        public void onPermissionGranted() {
//            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onPermissionDenied(List<String> deniedPermissions) {
//            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
//        }
//    };
}