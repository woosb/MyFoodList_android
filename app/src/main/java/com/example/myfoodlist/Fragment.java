package com.example.myfoodlist;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import org.jetbrains.annotations.NotNull;

public class Fragment extends AppCompatActivity {
    Button btn_menu, btn_list, btn_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btn_menu = findViewById(R.id.btn_menu);
        btn_list = findViewById(R.id.btn_list);
        btn_settings = findViewById(R.id.btn_settings);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                MapsFragment mapsFragment = new MapsFragment();
                transaction.replace(R.id.frame, mapsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                BlankFragment blankFragment = new BlankFragment();
                transaction.replace(R.id.frame, blankFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}