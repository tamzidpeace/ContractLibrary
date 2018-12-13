package com.example.arafat.contractlibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Contact_Library")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String contactName;
    @NonNull
    private int mobileNo;
    private int email;

    // public contnstructor

    public Contact(String contactName) {
        this.contactName = contactName;
        /*this.mobileNo = mobileNo;
        this.email = email;*/
    }

    // Getter & Setter Methods


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @NonNull
    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(@NonNull int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }
}
