package com.example.myfoodlist.main;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import com.example.myfoodlist.R;
import com.example.myfoodlist.samples.RoomDbEx;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private ImageView btn_open_menu;
    private ImageView btn_add_store;

    private Button btn_close_menu;

    private DrawerLayout drawerLayout;
    private View drawerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);

        btn_close_menu = findViewById(R.id.btn_close_menu);
        btn_close_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.close();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        // default fragment 설정
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new MapsFragment()).commit();
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull @NotNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull @NotNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull @NotNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}