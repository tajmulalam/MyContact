package com.example.sumon.mycontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameET, phoneNumberET;
    Contact contact;
    ContactManager contactManager;
    Button saveContactBtn, updateContactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = (EditText) findViewById(R.id.nameET);
        phoneNumberET = (EditText) findViewById(R.id.phoneNumberET);
        saveContactBtn = (Button) findViewById(R.id.saveContactBtn);
        updateContactBtn = (Button) findViewById(R.id.updateContactBtn);
        contactManager = new ContactManager(this);
        int contactID = getIntent().getIntExtra("ID", 0);

        if (contactID>0){
            setTitle("Update Contact");
            final Contact haveToUpdateContact = contactManager.getContactById(contactID);
            nameET.setText(haveToUpdateContact.getName());
            phoneNumberET.setText(haveToUpdateContact.getPhoneNumber());
            saveContactBtn.setVisibility(View.INVISIBLE);
            updateContactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String updateName =nameET.getText().toString();
                    String updatePhoneno=phoneNumberET.getText().toString();
                    if (updateName.length()==haveToUpdateContact.getName().length() && updatePhoneno.length()==haveToUpdateContact.getPhoneNumber().length()){
                        Toast.makeText(MainActivity.this,"You have Make changes for update the contact",Toast.LENGTH_LONG).show();
                    }else{
                        Contact goingToUpdateContact=new Contact(updateName,updatePhoneno);
                       Boolean isUpdated= contactManager.updateContact(haveToUpdateContact.getId(),goingToUpdateContact);
                        if (isUpdated){
                            Toast.makeText(MainActivity.this,"Contact Updated",Toast.LENGTH_LONG).show();
                            Intent listIntent=new Intent(MainActivity.this,ContactListActivity.class);
                            startActivity(listIntent);
                        }else {
                            Toast.makeText(MainActivity.this,"Contact Not Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }else {
            updateContactBtn.setVisibility(View.INVISIBLE);
//            setEnabled(false)

        saveContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String phoneNumber = phoneNumberET.getText().toString();
                if (name.length() > 2 && phoneNumber.length() > 4) {
                    contact = new Contact(name, phoneNumber);
                    boolean isInserted = contactManager.addContact(contact);
                    if (isInserted) {
                        Toast.makeText(MainActivity.this, name + " " + phoneNumber + " Successfully inserted ", Toast.LENGTH_SHORT).show();
                        Intent listIntent = new Intent(MainActivity.this, ContactListActivity.class);
                        startActivity(listIntent);
                    } else {
                        Toast.makeText(MainActivity.this, name + " " + phoneNumber + " not inserted", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(MainActivity.this, "LOL!. Fill Properly", Toast.LENGTH_SHORT).show();

                }
                nameET.getText().clear();
                phoneNumberET.getText().clear();
            }
        });
        }
    }

    public void goToList(View view)
    {
        Intent listIntent=new Intent(MainActivity.this,ContactListActivity.class);
        startActivity(listIntent);
    }


}
