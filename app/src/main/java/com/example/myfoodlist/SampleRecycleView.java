package com.example.myfoodlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.room.MainAdapter;
import com.example.myfoodlist.room.MainData;
import com.example.myfoodlist.room.RoomDb;

import java.util.ArrayList;
import java.util.List;

public class SampleRecycleView extends AppCompatActivity {

    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_recycle_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(SampleRecycleView.this, dataList);
        recyclerView.setAdapter(adapter);
    }
}