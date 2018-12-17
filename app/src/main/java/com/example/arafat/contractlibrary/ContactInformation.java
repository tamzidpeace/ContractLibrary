package com.example.arafat.contractlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);
        setTitle("Contact Information");

        TextView contactName = findViewById(R.id.contact_name_info);
        TextView contactPhone = findViewById(R.id.contact_phone_info);
        TextView contactEmail = findViewById(R.id.contact_email_info);

        Intent getData = getIntent();

        contactName.setText(getData.getStringExtra("name"));
        contactPhone.setText(getData.getStringExtra("phone"));
        contactEmail.setText(getData.getStringExtra("email"));
    }
}
