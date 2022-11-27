package com.example.myfoodlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.widget.*;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myfoodlist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EditText et_userId;
    private Button btn_login;
    private Button btn_move;
    private TextView tv_main;

    private EditText et_subParam;
    private String str_subParam;

    private ImageView img_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_main = findViewById(R.id.tv_main);
        et_userId = findViewById(R.id.et_userId);
        btn_login = findViewById(R.id.btn_login);
        btn_move = findViewById(R.id.btn_move);
        et_subParam = findViewById(R.id.et_subParam);
        img_main = findViewById(R.id.img_main);

        img_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "이미지 클릭", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("str_subParma", str_subParam);
                startActivity(intent); // 엑티비티 이동
            }
        });
    }
}