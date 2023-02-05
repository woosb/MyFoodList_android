package com.example.myfoodlist.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myfoodlist.R;
import com.example.myfoodlist.common.CommonUtil;

public class SearchAddrActivity extends AppCompatActivity {
    private WebView webView;
    private TextView txt_address;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_addr);

        txt_address = findViewById(R.id.txt_address);

        // WebView 초기화
        init_webView();

        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();
    }
    public void init_webView() {
        // WebView 설정
        webView = (WebView) findViewById(R.id.webView_address);

        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);

        // JavaScript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");

        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(new WebChromeClient());

        // webview url load. php 파일 주소
        webView.loadUrl(CommonUtil.getPostCodeUrl());
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String addr) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    txt_address.setText(addr);

                    Log.d("주소 : ", addr);
                    // WebView를 초기화 하지않으면 재사용할 수 없음
                    init_webView();

                    Intent intent = new Intent(SearchAddrActivity.this, AddStoreDetailActivity.class);
                    intent.putExtra("addr", addr);
                    startActivity(intent);
                }
            });
        }
    }
}