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

    Button btDelete, btEdit;
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
                Intent intent = new Intent(StoreListActivity.this, AddStoreDetailActivity.class);
                startActivity(intent);
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