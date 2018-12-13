package com.example.arafat.contractlibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    }
}
