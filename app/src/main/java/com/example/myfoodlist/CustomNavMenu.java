package com.example.myfoodlist;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.myfoodlist.R;
import org.jetbrains.annotations.NotNull;

public class CustomNavMenu extends AppCompatActivity {

    private Button btn_openMenu;
    private Button btn_closeMenu;

    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btn_openMenu = findViewById(R.id.btn_openMenu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nav_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);

        btn_openMenu = findViewById(R.id.btn_openMenu);
        btn_openMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        btn_closeMenu = findViewById(R.id.btn_closeMenu);
        btn_closeMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });


        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return true;
            }
        });
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