package com.example.myfoodlist;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SubActivity extends AppCompatActivity {

    private String str_param;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        str_param = intent.getStringExtra("str_subParma");

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(str_param);
    }
}