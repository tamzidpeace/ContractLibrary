package com.example.arafat.contractlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddEditNewContact extends AppCompatActivity {

    public static final String CONTACT_NAME = "com.example.android.wordlistsql.CONTACT_NAME";
    public static final String MOBILE_NO = "com.example.android.wordlistsql.MOBILE_NO";
    public static final String EMAIL = "com.example.android.wordlistsql.EMAIL";
    public static final String EXTRA_ID = "com.example.android.wordlistsql.ID";
    private static final String debug = "testing";
    private static final String debug2 = "testingid";

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
                int id = getIntent().getIntExtra(EXTRA_ID, -1);
                Log.d(debug, String.valueOf(id));

                if (id != -1) {
                    replyIntent.putExtra(EXTRA_ID, id);
                }
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        // updating information

        Intent editIntent = getIntent();

        if (editIntent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Information");
            int id2 = editIntent.getIntExtra("id", 1);
            Log.d(debug2, String.valueOf(id2));
            contactName.setText(editIntent.getStringExtra(CONTACT_NAME));
            contactMobileNo.setText(editIntent.getStringExtra(MOBILE_NO));
            contactEmail.setText(editIntent.getStringExtra(EMAIL));
        }
    }
}
