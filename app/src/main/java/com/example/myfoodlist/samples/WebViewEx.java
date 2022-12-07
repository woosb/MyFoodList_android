package com.example.myfoodlist.samples;


import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myfoodlist.R;

public class WebViewEx extends AppCompatActivity {

    private WebView webView;
    private String url = "https://www.naver.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClientClass());
        webView.setWebViewClient(new SslWebViewConnect());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}