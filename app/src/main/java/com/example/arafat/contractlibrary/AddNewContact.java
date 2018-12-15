package com.example.arafat.contractlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewContact extends AppCompatActivity {

    public static final String CONTACT_NAME = "com.example.android.wordlistsql.CONTACT_NAME";
    public static final String MOBILE_NO = "com.example.android.wordlistsql.MOBILE_NO";
    public static final String EMAIL = "com.example.android.wordlistsql.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        final EditText contactName, contactMobileNo, contactEmail;
        contactName = findViewById(R.id.contact_name2);
        contactMobileNo = findViewById(R.id.contact_mobile_no);
        contactEmail = findViewById(R.id.contact_email);
        Button saveContact = findViewById(R.id.save_button);

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = contactName.getText().toString();
                String mobileNo = contactMobileNo.getText().toString();
                String email = contactEmail.getText().toString();

                Intent replyIntent = new Intent();
                replyIntent.putExtra(CONTACT_NAME, name);
                replyIntent.putExtra(MOBILE_NO, mobileNo);
                replyIntent.putExtra(EMAIL, email);
                setResult(RESULT_OK, replyIntent);

                finish();
            }
        });
    }
}
