package com.strendent.tutorsu.Models;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Irfan Ali on 6/15/2015.
 */
public class PaymentItem implements Serializable {


    private Drawable cardImageDrawable;
    private String cardNumber;
    private boolean isPrimaryCard;

    public PaymentItem(Drawable cardImageDrawable, String cardNumber, boolean isPrimaryCard) {
        this.cardImageDrawable = cardImageDrawable;
        this.cardNumber = cardNumber;
        this.isPrimaryCard = isPrimaryCard;
    }

    public Drawable getCardImageDrawable() {
        return cardImageDrawable;
    }

    public void setCardImageDrawable(Drawable cardImageDrawable) {
        this.cardImageDrawable = cardImageDrawable;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isPrimaryCard() {
        return isPrimaryCard;
    }

    public void setIsPrimaryCard(boolean isPrimaryCard) {
        this.isPrimaryCard = isPrimaryCard;
    }
}
