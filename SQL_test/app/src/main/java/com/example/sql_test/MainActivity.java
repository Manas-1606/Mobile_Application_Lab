package com.example.sql_test;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private Button submitButton, showButton;

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        submitButton = findViewById(R.id.submitButton);
        showButton = findViewById(R.id.showButton);

        // create the database
        //database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);
        //database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, email VARCHAR)");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                // insert user into the database
                input_to_db(name, email);


            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                // get all users from the database
                Cursor cursor = database.rawQuery("SELECT * FROM users", null);

                List<User> users = new ArrayList<>();
                while (cursor.moveToNext()) {
                    String name = cursor.getString(0);
                    String email = cursor.getString(1);
                    User user = new User(name, email);
                    users.add(user);
                }
                cursor.close();
*/
                // pass the list of users to the next activity
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                //intent.putExtra("users", users.toArray(new User[0]));
                startActivity(intent);
            }
        });
    }


    private void input_to_db(String n, String e)
    {
        String msg = new dbmanager(this).addrecord(n, e);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

        // clear the input fields
        nameEditText.setText("");
        emailEditText.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}