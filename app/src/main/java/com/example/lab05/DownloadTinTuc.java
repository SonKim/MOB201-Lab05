package com.example.lab05;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTinTuc extends AsyncTask<String,Void,List<TinTuc>> {
    MainActivity activity = null;
    public  DownloadTinTuc(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected List<TinTuc> doInBackground(String... strings) {

        TinTucLoader loader = new TinTucLoader();
        List<TinTuc> list = new ArrayList<TinTuc>();

        // tạo url Connection để tải RSS
        String urlRss = strings[0];

        try {
            URL url = new URL(urlRss);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if(urlConnection.getResponseCode() ==200){
                // kết nối thành công thì mới lấy luồng dữ liệu
                InputStream inputStream = urlConnection.getInputStream();
                list = loader.getTinTucList( inputStream );

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(List<TinTuc> tinTucs) {
        super.onPostExecute(tinTucs);

        Log.d("zzzzz", "onPostExecute: số lượng bài viết = " + tinTucs.size());
        for(int i = 0; i< tinTucs.size(); i++){
            Log.d("zzzzz", "Tiêu đề bài viết:  " + tinTucs.get(i).getTitle()  );
        }
        // đổ lên listView thì viết code ở đây
        // ListView lv = activity.findViewById(R.id.lv01);
        // làm tiếp với adapter....
        ListView lvListView = activity.findViewById(R.id.lvListView);
        Adapter adapter = new Adapter(activity,tinTucs);
        lvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(activity,MainActivity2.class);
                intent.putExtra("link",tinTucs.get(i).getLink());
                activity.startActivity(intent);
            }
        });
        lvListView.setAdapter(adapter);
        /*
        quay sang activity viết code thực thi như sau:
         String urlRss = "https://vnexpress.net/rss/cuoi.rss";

        DownloadTinTuc downloadTinTuc = new DownloadTinTuc(MainActivity.this);
        downloadTinTuc.execute(urlRss );
         */


    }
}
