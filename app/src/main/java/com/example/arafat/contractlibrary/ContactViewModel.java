package com.example.arafat.contractlibrary;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository mContactRepository;
    private LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        mContactRepository = new ContactRepository(application);
        mAllContacts = mContactRepository.getmAllContacts();
    }

    LiveData<List<Contact>> getmAllContacts() {
        return mAllContacts;
    }

    public void insert(Contact contact) {
        mContactRepository.insert(contact);
    }
}
