package com.example.arafat.contractlibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Contact_Library")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String contactName;
    private String mobileNo;
    private String email;

    // public contnstructor

    public Contact(String contactName, String mobileNo, String email) {
        this.contactName = contactName;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    // Getter & Setter Methods


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getContactName() {
        return contactName;
    }

    String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
