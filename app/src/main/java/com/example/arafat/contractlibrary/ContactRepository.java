package com.example.arafat.contractlibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class ContactRepository {

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

    void deleteAllContact() {
        new deleteAllAsyncTask(mContactDao).execute();
    }

    void delete(Contact contact) {
        new deleteAsyncTask(mContactDao).execute(contact);
    }

    void update(Contact contact) {
        new updateAsyncTask(mContactDao).execute(contact);
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

    private static class deleteAllAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDAO asyncTaskDao;

        deleteAllAsyncTask(ContactDAO mContactDao) {
            asyncTaskDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            asyncTaskDao.deleteAllContact();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDAO asyncTaskDao;

        deleteAsyncTask(ContactDAO mContactDao) {
            asyncTaskDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            asyncTaskDao.deleteSingleContact(contacts[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class updateAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDAO asyncTaskDao;

        updateAsyncTask(ContactDAO mContactDao) {
            asyncTaskDao = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            asyncTaskDao.update(contacts[0]);
            return null;
        }
    }
}
