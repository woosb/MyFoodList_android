package com.example.myfoodlist.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.room.*;

import java.util.ArrayList;
import java.util.List;

public class StoreListActivity extends AppCompatActivity {

    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<StoreData> storeDataList = new ArrayList<>();
    StoreDb database;
    StoreListAdapter adapter;
    EditText et_name, et_addr, et_score, et_memo;

    private String longitude;
    private String latitude;
    String title, addr, memo;
    double score;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        Intent intent = getIntent();
        longitude = intent.getStringExtra("longitude");
        latitude = intent.getStringExtra("latitude");

        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        database = StoreDb.getInstance(this);

        storeDataList = database.storeDataDao().getAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StoreListAdapter(StoreListActivity.this, storeDataList);

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                title = et_name.getText().toString();
                addr = et_addr.getText().toString();
                score = Double.parseDouble(et_score.getText().toString());
                memo = et_memo.getText().toString();

                StoreData storeData = new StoreData();
                storeData.setName(title);
                storeData.setAddress(addr);
                storeData.setScore(score);
                storeData.setMemo(memo);
                storeData.setLatitude(Double.parseDouble(latitude));
                storeData.setLongitude(Double.parseDouble(longitude));

                Log.e("storeData", storeData.toString());

                database.storeDataDao().insert(storeData);
                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());

                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                database.storeDataDao().reset(storeDataList);
                storeDataList.clear();
                storeDataList.addAll(database.storeDataDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}