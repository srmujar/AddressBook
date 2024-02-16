package com.mujar.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsUpdated extends Activity {

    DatabaseHelper myDB;
    TextView viewName, viewPhone, viewCompany, viewEmail;
    Button editButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_updated);

        viewName = (TextView)findViewById(R.id.txtnameView);
        viewPhone = (TextView)findViewById(R.id.txtphoneView);
        viewCompany = (TextView)findViewById(R.id.txtcompanyView);
        viewEmail = (TextView)findViewById(R.id.txtemailView);
        editButton = (Button)findViewById(R.id.btn_edit);
        backButton = (Button)findViewById(R.id.btn_back);
        myDB = new DatabaseHelper(this);

        //receives the data form MainActivity class by using the 'setText' command
        String displayName = getIntent().getStringExtra("Name");
        viewName.setText(displayName);
        String displayPhone = getIntent().getStringExtra("Phone");
        viewPhone.setText(displayPhone);
        String displayCompany = getIntent().getStringExtra("Company");
        viewCompany.setText(displayCompany);
        String displayEmail = getIntent().getStringExtra("Email");
        viewEmail.setText(displayEmail);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = viewName.getText().toString();
                String phone = viewPhone.getText().toString();
                String company = viewCompany.getText().toString();
                String email = viewEmail.getText().toString();
                Cursor isUpdate = myDB.getItemID(name, phone, company, email);
                int itemId = -1;
                while (isUpdate.moveToNext()){
                    itemId = isUpdate.getInt(0);
                }
                if (itemId > -1){
                    Intent intent = new Intent(DetailsUpdated.this, EditActivity.class);
                    intent.putExtra("id", itemId);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("company", company);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsUpdated.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}