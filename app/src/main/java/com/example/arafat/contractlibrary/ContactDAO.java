package com.example.arafat.contractlibrary;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

// Necessary queries for database

@Dao
public interface ContactDAO {

    @Insert
    void insertContact(Contact contact);

    @Delete
    void deleteSingleContact(Contact contact);

    @Query("DELETE FROM Contact_Library")
    void deleteAllContact();

    @Query("SELECT * FROM Contact_Library")
    LiveData<List<Contact>> getAllContact();
}
