package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView tv1, tv2;
    String recv_list[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);

        Intent intent = getIntent();
        recv_list = intent.getStringArrayExtra(MainActivity.EXTRA_NAME);
        String contents = "";

        for(int i=0;i<3;i++)
        {
            contents = contents + " " + recv_list[i];
        }

        tv1.setText(contents);

        Toast.makeText(getApplicationContext(),contents,Toast.LENGTH_SHORT).show();

    }
}