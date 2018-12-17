package com.example.arafat.contractlibrary;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Contact.class}, version = 2)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase instance;

    //this is an abstract getter method to work with this database

    public abstract ContactDAO contactDAO();

    static ContactDatabase getDatabase(final Context context) {

        if (instance == null) {
            synchronized (ContactDatabase.class) {
                if (instance == null) {

                    // creating database

                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class, "contact_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return instance;
    }

    // creating callback method

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContactDAO mDao;
        //String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(ContactDatabase db) {
            mDao = db.contactDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //mDao.deleteAllContact();

            /*for (int i = 0; i < words.length-1; i++) {
                Contact contact = new Contact("Tamzid", "123456", "tamjedpeace@gmail.com");
                mDao.insertContact(contact);
            }*/
            return null;
        }

    }
}
