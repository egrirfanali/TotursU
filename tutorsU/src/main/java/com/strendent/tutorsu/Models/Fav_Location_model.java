package com.strendent.tutorsu.Models;

/**
 * Created by user on 6/16/2015.
 */
public class Fav_Location_model {
    String title;
    String primaryAddress;
    String secondryAddress;

    public Fav_Location_model(String title,String primaryAddress,
            String secondryAddress) {
        this.title = title;
        this.primaryAddress = primaryAddress;
        this.secondryAddress = secondryAddress;

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getSecondryAddress() {
        return secondryAddress;
    }

    public void setSecondryAddress(String secondryAddress) {
        this.secondryAddress = secondryAddress;
    }
}
