package com.example.myfoodlist.samples;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myfoodlist.R;
import com.example.myfoodlist.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {

    private String str_param;
    private TextView tv_title;

    private ListView lv_foodList;

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        str_param = intent.getStringExtra("str_subParma");
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(str_param);

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent); // 엑티비티 이동
            }
        });


        //food list view 데이터 설정
        lv_foodList = findViewById(R.id.lv_foodList);
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv_foodList.setAdapter(adapter);

       lv_foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                data.get(i);
                adapter.notifyDataSetChanged();
            }
        });

        data.add("test1");
        data.add("test2");
        data.add("test3");
        adapter.notifyDataSetChanged();

    }

}