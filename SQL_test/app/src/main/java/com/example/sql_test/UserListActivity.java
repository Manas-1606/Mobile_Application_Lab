package com.example.sql_test;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.listView);

        // get the list of users from the previous activity
        /*
        User[] users = (User[]) getIntent().getSerializableExtra("users");
        */
        // create an array list to hold the list of users
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<User> dataholder;
        /*
        for (User user : users) {
            userList.add(user);
        }
        */

        Cursor cursor = new dbmanager(this).readalldata();
        while (cursor.moveToNext()) {
            User user = new User(cursor.getString(1), cursor.getString(2));
            userList.add(user);
        }
       // cursor.close();
        // create an array adapter to display the list of users in the ListView
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(adapter);
    }
/*
    private void output_from_db()
    {
        Cursor cursor = new dbmanager(this).readalldata();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            User user = new User(name, email);
            //userList.add(user);
        }
        cursor.close();

    }

*/
}