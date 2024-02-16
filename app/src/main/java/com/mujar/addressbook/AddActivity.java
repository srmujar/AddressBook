package com.mujar.addressbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAddData,btnViewData;
    EditText editName, editPhone, editCompany, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = (EditText) findViewById(R.id.txtName);
        editPhone = (EditText) findViewById(R.id.txtPhone);
        editCompany = (EditText) findViewById(R.id.txtCompany);
        editEmail = (EditText) findViewById(R.id.txtEmail);
        btnAddData = (Button) findViewById(R.id.btn_addData);
        btnViewData = (Button) findViewById(R.id.btn_viewData);


        myDB = new DatabaseHelper(this);

        //records data; else display's a message
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String phone = editPhone.getText().toString();
                String company = editCompany.getText().toString();
                String email = editEmail.getText().toString();
                if(editName.length()!= 0 && editPhone.length() != 0 && editCompany.length() != 0 && editEmail.length() != 0){
                    AddData(name, phone, company, email);
                    editName.setText("");
                    editPhone.setText("");
                    editCompany.setText("");
                    editEmail.setText("");
                }else{
                    Toast.makeText(AddActivity.this, "Please fill in the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        //view the data
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String etName, String etPhone, String etCompany, String etEmail) {

        boolean insertData = myDB.addData(etName, etPhone, etCompany, etEmail);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
