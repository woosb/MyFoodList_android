package com.example.myfoodlist;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.example.myfoodlist.room.RoomDb;

public class MainActivity extends AppCompatActivity {
    Button btn_menu, btn_list, btn_settings;
    Button btn_test1, btn_test2, btn_test3, btn_test4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MapsFragment mapsFragment = new MapsFragment();
        transaction.replace(R.id.frame, mapsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

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

        btn_settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SampleRecycleView.class);
                startActivity(intent);
            }
        });
    }
}