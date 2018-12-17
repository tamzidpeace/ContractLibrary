package com.example.arafat.contractlibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String debug = "contractList";

    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    private ContactViewModel viewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int NEW_WORD_EDIT_ACTIVITY_REQUEST_CODE = 2;

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
                Intent intent = new Intent(MainActivity.this, AddEditNewContact.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        // Add the functionality to swipe items in the
        // recycler view to delete that item

        // ItemTouchHelper  class will help use to complete this task

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT |
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Contact contact1 = mAdapter.getContactPostion(position);
                viewModel.delete(contact1);
            }


        });
        helper.attachToRecyclerView(mRecyclerView);


        // work for showing information of a contact


        mAdapter.setOnItemClickListener(new ContactListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contact contact) {
                String name = contact.getContactName();
                String email = contact.getEmail();
                String phoneNumber = contact.getMobileNo();

                Intent sendIntent = new Intent(MainActivity.this, ContactInformation.class);
                sendIntent.putExtra("name", name);
                sendIntent.putExtra("phone", phoneNumber);
                sendIntent.putExtra("email", email);
                startActivity(sendIntent);
                Log.d(debug, "working click");

            }
        });

        // work for long press delete information information

        mAdapter.setOnLongItemClickListener(new ContactListAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(final Contact contact) {
                Log.d(debug, "working from ContactList");
                Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(), AddEditNewContact.class);
                editIntent.putExtra(AddEditNewContact.CONTACT_NAME, contact.getContactName());
                editIntent.putExtra(AddEditNewContact.MOBILE_NO, contact.getMobileNo());
                editIntent.putExtra(AddEditNewContact.EMAIL, contact.getEmail());
                editIntent.putExtra(AddEditNewContact.EXTRA_ID, contact.getId());
                startActivityForResult(editIntent, NEW_WORD_EDIT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditNewContact.CONTACT_NAME);
            String mobile = data.getStringExtra(AddEditNewContact.MOBILE_NO);
            String email = data.getStringExtra(AddEditNewContact.EMAIL);
            Contact contact = new Contact(name, mobile, email);
            viewModel.insert(contact);

        } else if (requestCode == NEW_WORD_EDIT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditNewContact.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(getApplicationContext(), "can't be update", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditNewContact.CONTACT_NAME);
            String mobile = data.getStringExtra(AddEditNewContact.MOBILE_NO);
            String email = data.getStringExtra(AddEditNewContact.EMAIL);
            Contact contact = new Contact(name, mobile, email);
            contact.setId(id);
            viewModel.update(contact);
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();

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

    // delete all contacts with alert dialogue box

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_data:
                new AlertDialog.Builder(this)
                        .setTitle("Attention!")
                        .setMessage("Do you really want to delete all Contacts?")
                        .setCancelable(true)
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
