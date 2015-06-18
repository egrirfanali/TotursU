package com.strendent.tutorsu.Models;

import java.io.Serializable;

/**
 * Created by Irfan Ali on 6/15/2015.
 */
public class PaymentItem implements Serializable {


//    private Drawable cardImageDrawable;
    private String cardNumber;
    private boolean isPrimaryCard;
    private String securityCode;
    private String expDateString;
    byte[] byteArray;

    /* Drawable drawable = imageViewCardType.getDrawable();
                            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();*/


    public PaymentItem(String cardNumber, boolean isPrimaryCard, String securityCode, String expDateString,byte[] byteArray) {
//        this.cardImageDrawable = cardImageDrawable;
        this.cardNumber = cardNumber;
        this.isPrimaryCard = isPrimaryCard;
        this.securityCode=securityCode;
        this.expDateString=expDateString;
        this.byteArray=byteArray;
    }


    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpDateString() {
        return expDateString;
    }

    public void setExpDateString(String expDateString) {
        this.expDateString = expDateString;
    }

//    public Drawable getCardImageDrawable() {
//        return cardImageDrawable;
//    }
//
//    public void setCardImageDrawable(Drawable cardImageDrawable) {
//        this.cardImageDrawable = cardImageDrawable;
//    }

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
