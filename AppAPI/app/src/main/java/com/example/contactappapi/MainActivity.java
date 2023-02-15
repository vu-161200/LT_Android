package com.example.contactappapi;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init list
        new FetchAPI(this).execute();

        // Init
        lvContact = (ListView) findViewById(R.id.lvContact);

    }
    public void InitAdapter(List<ContactModel> contacts){
        // Add adapter
        ContactAdapter adapter = new ContactAdapter(contacts, this);
        lvContact.setAdapter(adapter);
        // Register Context Menu <=> Hold item on listview
        registerForContextMenu(lvContact);

        // Click Event ==> Activity info
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);

                ContactModel contact = (ContactModel) adapterView.getAdapter().getItem(i);
                intent.putExtra("CONTACT_MODEL", contact);

                startActivity(intent);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Choose type: ");
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.call:
                Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mail:
                Toast.makeText(this, "Send Mail", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.message:
                Toast.makeText(this, "Send message", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
