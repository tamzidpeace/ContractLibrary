package com.example.arafat.contractlibrary;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ContactRepository {

    private ContactDAO mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    ContactRepository(Application application) {
        ContactDatabase db = ContactDatabase.getDatabase(application);
        mContactDao = db.contactDAO();
        mAllContacts = mContactDao.getAllContact();
    }

    LiveData<List<Contact>> getmAllContacts() {
        return mAllContacts;
    }

    void insert(Contact contact) {
        new insertAsyncTask(mContactDao).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDAO asyncTaskDao;

        insertAsyncTask(ContactDAO mContactDao) {
            asyncTaskDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            asyncTaskDao.insertContact(contacts[0]);
            return null;
        }
    }
}
