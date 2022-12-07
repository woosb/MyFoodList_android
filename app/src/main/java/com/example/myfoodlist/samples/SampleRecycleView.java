package com.example.myfoodlist.samples;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.sampleRecycleView.SampleActivity;
import com.example.myfoodlist.sampleRecycleView.SampleAdapter;
import com.example.myfoodlist.main.FoodCamera;

import java.util.ArrayList;
import java.util.List;

public class SampleRecycleView extends AppCompatActivity {

    RecyclerView recyclerView;

    List<SampleActivity> dataList = new ArrayList<>();
    SampleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_recycle_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, SubActivity.class), "SubActivity"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, NavActivity.class), "NavActivity"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, SharedPreferencesEx.class), "SharedPreferencesEx"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, WebViewEx.class), "WebViewEx"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, CustomNavMenu.class), "CustomNavMenu"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, FoodCamera.class), "FoodCamera"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, MainMapActivity.class), "MainMapActivity"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, RoomDbEx.class), "RoomDbEx"));
        dataList.add(new SampleActivity(new Intent(SampleRecycleView.this, MainActivity_old.class), "MainActivity_old"));

        adapter = new SampleAdapter(SampleRecycleView.this, dataList);
        recyclerView.setAdapter(adapter);
    }
}