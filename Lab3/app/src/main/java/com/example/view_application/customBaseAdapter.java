package com.example.view_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class customBaseAdapter extends BaseAdapter {

    Context context;
    String listfruit[];
    int listimages[];
    LayoutInflater inflater;

    public customBaseAdapter(Context ctx, String fruitlist[], int imagelist[]){
        this.context = ctx;
        this.listfruit = fruitlist;
        this.listimages = imagelist;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listfruit.length;
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
        view = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txt = (TextView) view.findViewById(R.id.image_text);
        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        txt.setText(listfruit[i]);
        img.setImageResource(listimages[i]);

        return view;
    }
}
