package com.example.myfoodlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity_old extends AppCompatActivity {

    private EditText et_userId;
    private Button btn_login;
    private Button btn_move;
    private Button btn_nav;
    private Button btn_sharedPreferences;
    private Button btn_webView;
    private Button btn_customNav;
    private Button btn_camera;
    private Button btn_map;
    private Button btn_room;
    private Button btn_frame;
    private TextView tv_main;

    private EditText et_subParam;
    private String str_subParam;

    private ImageView img_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

        tv_main = findViewById(R.id.tv_main);
        et_userId = findViewById(R.id.et_userId);

        btn_login = findViewById(R.id.btn_login);
        btn_move = findViewById(R.id.btn_move);
        btn_nav = findViewById(R.id.btn_nav);
        btn_sharedPreferences = findViewById(R.id.btn_sharedPreferences);
        btn_webView = findViewById(R.id.btn_webView);
        btn_customNav = findViewById(R.id.btn_customNav);
        btn_camera = findViewById(R.id.btn_camera);
        btn_map = findViewById(R.id.btn_map);
        btn_room = findViewById(R.id.btn_room);
        btn_frame = findViewById(R.id.btn_frame);

        et_subParam = findViewById(R.id.et_subParam);
        img_main = findViewById(R.id.img_main);


        img_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_main.setText("누르지 마세요");
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //딜레이 후 시작할 코드 작성
                        tv_main.setText("이것저것아무거나 내마음데로");
                    }
                }, 600);// 0.6초 정도 딜레이를 준 후 시작
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable text = et_userId.getText();
                et_userId.setText(text + "라고 하면 안되요");
            }
        });


        btn_move.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                str_subParam = et_subParam.getText().toString();
                Intent intent = new Intent(MainActivity_old.this, SubActivity.class);
                intent.putExtra("str_subParma", str_subParam);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_nav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, NavActivity.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_sharedPreferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, SharedPreferencesEx.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_webView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, WebViewEx.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_customNav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, CustomNavMenu.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, FoodCamera.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

       btn_map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, MainMapActivity.class);
                startActivity(intent); // 엑티비티 이동
            }
        });
       btn_room.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, RoomDbEx.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

        btn_frame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_old.this, MainActivity.class);
                startActivity(intent); // 엑티비티 이동
            }
        });
    }
}