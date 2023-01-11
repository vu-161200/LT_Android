package com.example.gmailgui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lvGmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        lvGmail = (ListView) findViewById(R.id.lvGmail);

        // Adapter
        GmailAdapter adapter = new GmailAdapter(GmailModel.gmails);

        // Assign adapter
        lvGmail.setAdapter(adapter);
    }
}