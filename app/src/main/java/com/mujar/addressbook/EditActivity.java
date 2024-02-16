package com.mujar.addressbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private int selectedID;
    private String selectedName;
    private String selectedPhone;
    private String selectedCompany;
    private String selectedEmail;
    EditText editName, editPhone, editCompany, editEmail;
    Button btnUpdateData, btnDeleteData, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editName = (EditText) findViewById(R.id.edit_name);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editCompany = (EditText) findViewById(R.id.edit_company);
        editEmail = (EditText) findViewById(R.id.edit_email);
        btnUpdateData = (Button) findViewById(R.id.btn_updateData);
        btnDeleteData = (Button) findViewById(R.id.btn_deleteData);
        btnBack = (Button) findViewById(R.id.btn_back);
        myDb = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");
        selectedPhone = receivedIntent.getStringExtra("phone");
        selectedCompany = receivedIntent.getStringExtra("company");
        selectedEmail = receivedIntent.getStringExtra("email");
        editName.setText(selectedName);
        editPhone.setText(selectedPhone);
        editCompany.setText(selectedCompany);
        editEmail.setText(selectedEmail);

        btnUpdateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editName.getText().toString();
                        String phone = editPhone.getText().toString();
                        String company = editCompany.getText().toString();
                        String email = editEmail.getText().toString();
                        if ((!name.equals("") && !phone.equals("") && !company.equals("") && !email.equals(""))) {
                            Toast.makeText(EditActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                            myDb.updateData(name, phone, company, email, selectedID, selectedName, selectedPhone, selectedCompany, selectedEmail);
                            Intent intent = new Intent(EditActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditActivity.this, "Please fill in the fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete contact " + selectedName + "?");
                builder.setCancelable(false);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDb.deleteData(selectedID, selectedName, selectedPhone, selectedCompany, selectedEmail);
                        editName.setText("");
                        editPhone.setText("");
                        editCompany.setText("");
                        editEmail.setText("");
                        Intent intent = new Intent(EditActivity.this, MainActivity.class);
                        //intent.putExtra("DetailsUpdated", editName.getText().toString());
                        //intent.putExtra("DetailsUpdated", editPhone.getText().toString());
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, DetailsUpdated.class);
                intent.putExtra("Name", editName.getText().toString());
                intent.putExtra("Phone", editPhone.getText().toString());
                intent.putExtra("Company", editCompany.getText().toString());
                intent.putExtra("Email", editEmail.getText().toString());
                startActivity(intent);
            }
        });
    }
}