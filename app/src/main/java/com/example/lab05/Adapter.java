package com.example.lab05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    Context context;
    List<TinTuc> tinTucs;

    public Adapter(Context context, List<TinTuc> tinTucs) {
        this.context = context;
        this.tinTucs = tinTucs;
    }

    @Override
    public int getCount() {
        return tinTucs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_view_layout,null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(tinTucs.get(i).getTitle());
        TextView tvDescription = view.findViewById(R.id.tvDescription);
        tvDescription.setText(tinTucs.get(i).getDescription());
        return view;
    }
}
