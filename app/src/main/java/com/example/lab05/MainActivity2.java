package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button btnTroVe;
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnTroVe = findViewById(R.id.btnTroVe);
        webview = findViewById(R.id.webview);
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        webview.loadUrl(link);
        webview.setWebViewClient(new WebViewClient());
    }
}