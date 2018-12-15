package com.example.arafat.contractlibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
