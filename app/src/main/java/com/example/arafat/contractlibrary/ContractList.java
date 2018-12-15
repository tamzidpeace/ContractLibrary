package com.example.arafat.contractlibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ContractList extends AppCompatActivity {

    private static final String debug = "contractList";

    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    private ContactViewModel viewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_list);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        final ContactListAdapter mAdapter = new ContactListAdapter(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        viewModel.getmAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                mAdapter.setmContract(contacts);
            }
        });

        FloatingActionButton addContact;
        addContact = findViewById(R.id.add_contact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContractList.this, AddNewContact.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
           String name = data.getStringExtra(AddNewContact.CONTACT_NAME);
           String mobile = data.getStringExtra(AddNewContact.MOBILE_NO);
           String email = data.getStringExtra(AddNewContact.EMAIL);

           Contact contact = new Contact(name, mobile, email);
           viewModel.insert(contact);

        } else {
            Toast.makeText(
                    getApplicationContext(), "Not Saved", Toast.LENGTH_LONG).show();
        }
    }

    // it will show delete icon(menu item)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_data:
                new AlertDialog.Builder(this)
                        .setTitle("Attention!")
                        .setMessage("Do you really want to delete all Contacts?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewModel.deleteAll();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
