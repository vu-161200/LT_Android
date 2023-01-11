package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactInfo extends AppCompatActivity {
    TextView id;
    TextView name;
    TextView email;
    TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Init
        id = (TextView) findViewById(R.id.contact_info_id);
        name = (TextView) findViewById(R.id.contact_info_name);
        email = (TextView) findViewById(R.id.contact_info_email);
        phoneNumber = (TextView) findViewById(R.id.contact_info_phone_number);

        // Get info
        Intent intent = getIntent();
        ContactModel contact = (ContactModel) intent.getSerializableExtra("CONTACT_MODEL");

        // Assign value
        id.setText("ID: " + contact.getId());
        name.setText(contact.getName());
        email.setText("Email: " + contact.getEmail());
        phoneNumber.setText("Phone Number: " + contact.getPhoneNumber());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}