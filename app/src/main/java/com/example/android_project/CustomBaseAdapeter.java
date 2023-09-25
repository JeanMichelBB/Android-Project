package com.example.android_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapeter extends BaseAdapter {
    Context context;
    String filmList[];
    int filmImages[];
    LayoutInflater inflater;
    public CustomBaseAdapeter(Context ctx, String filmList[], int filmImages[]) {
        this.context = ctx;
        this.filmList = filmList;
        this.filmImages = filmImages;
        inflater = LayoutInflater.from(ctx);

    }
    @Override
    public int getCount() {
        return filmList.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        textView.setText(filmList[position]);
        imageView.setImageResource(filmImages[position]);
        return convertView;
    }
}
