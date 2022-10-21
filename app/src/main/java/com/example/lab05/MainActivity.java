package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText edLink;
    Button btnTimKiem;
    ListView lvListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edLink = findViewById(R.id.edLink);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        lvListView = findViewById(R.id.lvListView);
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlRss = edLink.getText().toString();

                DownloadTinTuc downloadTinTuc = new DownloadTinTuc(MainActivity.this);
                downloadTinTuc.execute(urlRss );
            }
        });
    }
    boolean CheckNetworkConnect(){
        ConnectivityManager cnn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // kiểm tra đối với wifi
        NetworkInfo networkInfo = cnn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifi = networkInfo.isConnected();

        // kiểm tra đối với 3G
        networkInfo = cnn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobile = networkInfo.isConnected();

        if(isWifi)
            Log.d("TAG", "CheckNetworkConnect: Đang kết nối Wifi");

        if(isMobile)
            Log.d("TAG", "CheckNetworkConnect: Đang kết nối Mobile Data (3G,4G,5G)");

        return isMobile || isWifi;
    }

}