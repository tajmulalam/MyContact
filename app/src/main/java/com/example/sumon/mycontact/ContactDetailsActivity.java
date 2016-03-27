package com.example.sumon.mycontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetailsActivity extends AppCompatActivity {
    ContactManager contactManager;
    Contact contact;
    TextView nameTV,phoneTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        nameTV= (TextView) findViewById(R.id.showNameTV);
        phoneTV= (TextView) findViewById(R.id.showPhoneTV);
        int contact_ID = getIntent().getIntExtra("contact_Id", 0);
        contactManager=new ContactManager(this);
        contact=contactManager.getContactById(contact_ID);
        nameTV.setText("NAME: "+contact.getName());
        phoneTV.setText("PHONE: "+contact.getPhoneNumber());


    }

    public void deleteContact(View view)
    {
        boolean isDeleted=contactManager.deleteContactById(contact.getId());

        if (isDeleted){
            Intent allContactIntent=new Intent(ContactDetailsActivity.this,ContactListActivity.class);
            startActivity(allContactIntent);
            Toast.makeText(ContactDetailsActivity.this,"Delete Successfull",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ContactDetailsActivity.this,"Delete Failed",Toast.LENGTH_LONG).show();

        }
    }

    public void updateContact(View view)
    {
        Intent updateDataIntent=new Intent(ContactDetailsActivity.this,MainActivity.class);
        updateDataIntent.putExtra("ID",contact.getId());
        startActivity(updateDataIntent);
    }
}
