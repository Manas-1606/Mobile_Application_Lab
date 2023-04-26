package com.example.sql_test;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button submitButton, showButton;
    String color, fn;
    Boolean rc, bc, gc, bgc, fc, dc;
    String[] clr = {"Red", "Blue", "Green"};
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        ListView listview = findViewById(R.id.lists);
        submitButton = findViewById(R.id.submitButton);
        showButton = findViewById(R.id.showButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clr);
        listview.setAdapter(adapter);

        registerForContextMenu(listview);



        // create the database
        //database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);
        //database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, email VARCHAR)");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                //String email = emailEditText.getText().toString();

                // insert user into the database
                input_to_db(name);


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
                if(rc == Boolean.TRUE)
                    color = "red";
                else if (bc == Boolean.TRUE)
                    color = "blue";
                else if (gc == Boolean.TRUE)
                    color = "green";

                if(bgc == Boolean.TRUE)
                    fn = "bg";
                else if (fc == Boolean.TRUE)
                    fn = "fc";
                else fn = "dc";
                intent.putExtra("color", color);
                intent.putExtra("function", fn);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.listview_menu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int pos = info.position;
        if(pos == 0)
                rc = Boolean.TRUE;
        else if(pos==1)
                bc = Boolean.TRUE;
        else if(pos==2)
                gc = Boolean.TRUE;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.bgc:
                bgc = Boolean.TRUE;
                return true;

            case R.id.font:
                fc = Boolean.TRUE;
                return true;

            case R.id.delete:
                dc = Boolean.TRUE;
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void input_to_db(String n)
    {
        String msg = new dbmanager(this).addrecord(n);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

        // clear the input fields
        nameEditText.setText("");
        //testemailEditText.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}