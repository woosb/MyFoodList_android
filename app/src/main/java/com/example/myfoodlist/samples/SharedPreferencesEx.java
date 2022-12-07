package com.example.myfoodlist.samples;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myfoodlist.R;

/*
* 앱 삭제시 데이터가 사라진다.
* 설정값 등을 저장할때 활용할 수 있다.(다크모드 등등)
* */
public class SharedPreferencesEx extends AppCompatActivity {

    private EditText et_save;
    private String shared = "TEXT_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        et_save = findViewById(R.id.et_save);

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("test", "");
        et_save.setText(value);
    }

    //activity가 종료될때
    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = et_save.getText().toString();
        editor.putString("test", value);
        editor.commit();

    }
}