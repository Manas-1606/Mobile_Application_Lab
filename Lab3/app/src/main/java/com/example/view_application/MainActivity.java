package com.example.view_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String fruitlist[] = {"apple", "banana", "mango", "watermelon", "pineapple"};
    int imagelist[] = {R.drawable.apple, R.drawable.banana, R.drawable.mango, R.drawable.watermelon, R.drawable.pineapple};

    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        customBaseAdapter custombaseadapter =  new customBaseAdapter(getApplicationContext(), fruitlist, imagelist);
        listview.setAdapter(custombaseadapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("CUSTOM_LIST_VIEW", "Item is clicked @position: " + i);
            }
        });
    }
}