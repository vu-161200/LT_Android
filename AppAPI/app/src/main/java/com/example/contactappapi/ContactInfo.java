package com.example.contactappapi;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
public class ContactInfo extends AppCompatActivity {
    ContactModel contact;
    ImageView avatar;
    TextView name;
    TextView username;
    TextView email;
    TextView phone;
    TextView address; // street + city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Init
        avatar = (ImageView) findViewById(R.id.contact_info_avatar);
        name = (TextView) findViewById(R.id.contact_info_name);
        username = (TextView) findViewById(R.id.contact_info_username);
        email = (TextView) findViewById(R.id.contact_info_email);
        phone = (TextView) findViewById(R.id.contact_info_phone);
        address = (TextView) findViewById(R.id.contact_info_address);

        // Get info
        Intent intent = getIntent();
        contact = (ContactModel) intent.getSerializableExtra("CONTACT_MODEL");

        // Assign value
        int id = getResources().getIdentifier(contact.getAvatar().getWall(), "drawable", getPackageName());
        avatar.setImageResource(id);
        name.setText(contact.getName());
        username.setText("Username: " + contact.getUsername());
        email.setText("Email: " + contact.getEmail());
        phone.setText("Phone: " + contact.getPhone());
        address.setText("Address: " + contact.getAddress().getStreet() + " " + contact.getAddress().getCity());

        // Click Êvent
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float lat = Float.parseFloat(contact.getAddress().getLat());
                float lng = Float.parseFloat(contact.getAddress().getLng());
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lng);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
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