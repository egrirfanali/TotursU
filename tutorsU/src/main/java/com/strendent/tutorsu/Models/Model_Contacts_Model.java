package com.strendent.tutorsu.Models;

/**
 * Created by user on 6/29/2015.
 */
public class Model_Contacts_Model {
    String name;
    String phoneNumber;
    public Model_Contacts_Model(String contactName,String contactPhoneNumber) {
        this.name = contactName;
        this.phoneNumber = contactPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
