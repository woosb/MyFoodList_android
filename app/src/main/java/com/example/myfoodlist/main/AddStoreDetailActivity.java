package com.example.myfoodlist.main;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.room.StoreData;
import com.example.myfoodlist.room.StoreDb;
import com.example.myfoodlist.room.StoreListAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class AddStoreDetailActivity extends AppCompatActivity {

    private static final int DEFAULT_GALLERY_REQUEST_CODE = 1;
    Button btn_add_detail;

    EditText et_name, et_addr, et_score, et_memo;
    ImageView iv_picture;

    RecyclerView recyclerView;

    String title, addr, memo;
    double score;

    List<StoreData> storeDataList = new ArrayList<>();
    StoreDb database;
    StoreListAdapter adapter;

    double latitude;
    double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store_detail);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude",0);
        longitude = intent.getDoubleExtra("longitude",0);

        String getAddrFromLatLng = getAddress(new LatLng(latitude, longitude));

        btn_add_detail = findViewById(R.id.btn_add_detail);
        et_name = findViewById(R.id.et_name);
        et_addr = findViewById(R.id.et_addr);
        et_score = findViewById(R.id.et_score);
        et_memo = findViewById(R.id.et_memo);
        iv_picture = findViewById(R.id.iv_picture);

        et_addr.setText(getAddrFromLatLng);

        recyclerView = findViewById(R.id.recycler_view);

        database = StoreDb.getInstance(this);
        storeDataList = database.storeDataDao().getAll();
        adapter = new StoreListAdapter(AddStoreDetailActivity.this, storeDataList);

        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        recyclerView.setAdapter(adapter);

        iv_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_menu1){
                            Intent intent = new Intent(AddStoreDetailActivity.this, FoodCamera.class);
                            startActivity(intent);
                            Toast.makeText(AddStoreDetailActivity.this, "메뉴 1 클릭", Toast.LENGTH_SHORT).show();
                        }else if (menuItem.getItemId() == R.id.action_menu2){
                            showGallery();
                            Toast.makeText(AddStoreDetailActivity.this, "메뉴 2 클릭", Toast.LENGTH_SHORT).show();
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
                score = Double.parseDouble(et_score.getText().toString());
                memo = et_memo.getText().toString();

                StoreData storeData = new StoreData();
                storeData.setName(title);
                storeData.setAddress(addr);
                storeData.setScore(score);
                storeData.setMemo(memo);
                storeData.setLatitude(latitude);
                storeData.setLongitude(longitude);

                Log.e("storeData", storeData.toString());

                database.storeDataDao().insert(storeData);
                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());

                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());
                adapter.notifyDataSetChanged();

            }
        });
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
        String addr = "주소 오류";

        try{
            List<Address> fromLocation = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            addr = fromLocation.get(0).getAddressLine(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return addr;
    }
}