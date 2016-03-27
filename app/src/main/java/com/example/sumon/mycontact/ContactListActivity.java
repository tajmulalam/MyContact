package com.example.sumon.mycontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    ContactManager contactManager;
    ArrayList<Contact>contactsList;
    ListView listView;
    AdapterForContact adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        contactManager=new ContactManager(this);
        listView= (ListView) findViewById(R.id.listView);
        contactsList=contactManager.getAllContact();
        adapter=new AdapterForContact(this,contactsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int contactID=contactsList.get(position).getId();
                Intent detailsIntent=new Intent(ContactListActivity.this,ContactDetailsActivity.class);
                detailsIntent.putExtra("contact_Id",contactID);
                startActivity(detailsIntent);

            }
        });
    }
}
