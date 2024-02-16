package com.mujar.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    Button btnAdd;
    DatabaseHelper myDB;
    ArrayList<Contacts> contactsList;
    ListView listView;
    Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        contactsList = new ArrayList<>();
        Cursor data = myDB.getAllData();
        int numRows = data.getCount();

        int i = 0;
        while (data.moveToNext()) {
            contacts = new Contacts(data.getString(1), data.getString(2), data.getString(3), data.getString(4));
            contactsList.add(i, contacts);
            System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4));
            System.out.println(contactsList.get(i).getName());
            i++;
        }
        final CustomAdapter adapter = new CustomAdapter(this, R.layout.list_row, contactsList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        btnAdd = (Button) findViewById(R.id.btn_add);

        //navigate to AddActivity class
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, AddActivity.class);
                        startActivity(intent);
                    }
                }
        );

        /*public void fillListView(){
            ListView listView = findViewById(R.id.listView);
            DatabaseHelper db = new DatabaseHelper(this);

            ArrayList<Contacts> contactsList = db.getAllData();

            CustomAdapter customAdapter = new CustomAdapter(contactsList, this);
            listView.setAdapter(customAdapter);
        }

        /*final ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getAllData();
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);

        }*/

        //sends the data to UpdateActivity class by using 'putExtra' command
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("Name", contactsList.get(position).getName());
                intent.putExtra("Phone", contactsList.get(position).getPhone());
                intent.putExtra("Company", contactsList.get(position).getCompany());
                intent.putExtra("Email", contactsList.get(position).getEmail());
                //intent.putExtra("Data", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
}