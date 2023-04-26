package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "list";
    CheckBox pizbox, burgbox, tacobox;
    Button btn;

    String list[] = {"0", "0", "0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizbox = findViewById(R.id.pizza);
        burgbox = findViewById(R.id.burger);
        tacobox = findViewById(R.id.taco);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizbox.isChecked()){
                    list[0] = "Pizza";
                }

                if(burgbox.isChecked()){
                    list[1] = "Burger";
                }

                if(tacobox.isChecked()){
                    list[2] = "Taco";
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(EXTRA_NAME, list);
                startActivity(intent);
            }
        });
    }


}