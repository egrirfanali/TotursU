package com.strendent.tutorsu.Activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.CardValidator;


public class Activity_CardPayment extends Activity {

    EditText edtCardCredent;
    ImageView imageViewCardType;
    // Change this to what you want... ' ', '-' etc..
    private static final char space = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        edtCardCredent= (EditText)  findViewById(R.id.edtCardCredent);
        imageViewCardType=(ImageView) findViewById(R.id.imageViewCardType);

        edtCardCredent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().startsWith("4")){
                    imageViewCardType.setImageResource(R.drawable.ic_visa);
                    imageViewCardType.setTag("ic_visa");
                }else  if(s.toString().startsWith("5")){
                    imageViewCardType.setImageResource(R.drawable.ic_mastercard);
                        imageViewCardType.setTag("ic_mastercard");
                }else  if(s.toString().startsWith("35")){
                    imageViewCardType.setImageResource(R.drawable.ic_jcb);
                    imageViewCardType.setTag("ic_jcb");
                }else  if(s.toString().startsWith("305") || s.toString().startsWith("385")){
                    imageViewCardType.setImageResource(R.drawable.ic_diners);
                    imageViewCardType.setTag("ic_diners");
                } else if(s.toString().startsWith("6")){
                    imageViewCardType.setImageResource(R.drawable.ic_discover);
                    imageViewCardType.setTag("ic_discover");
                }else if(s.toString().startsWith("37")){
                    imageViewCardType.setImageResource(R.drawable.ic_amex);
                    imageViewCardType.setTag("ic_amex");
                }else if(s.toString().startsWith("35")){
                    imageViewCardType.setImageResource(R.drawable.ic_jcb);
                    imageViewCardType.setTag("ic_jcb");
                }else{
                    imageViewCardType.setImageResource(R.drawable.ic_placeholder);
                        imageViewCardType.setTag("ic_placeholder");
                }

                if(s.toString().length()==19){

//                    s.toString().replaceAll("\\s+","");
                    if (CardValidator.validate(s.toString().replaceAll("\\s+",""))){

                        edtCardCredent.setTextColor(Color.BLACK);

                    }else{

                        edtCardCredent.setTextColor(Color.RED);
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
            }

        });


    }



//    @Override
//    public String getCardNumber() {
//        return this.cardNumber.getText().toString();
//    }
//
//    @Override
//    public String getCvc() {
//        return this.cvc.getText().toString();
//    }
//
//    @Override
//    public Integer getExpMonth() {
//        return getInteger(this.monthSpinner);
//    }
//
//    @Override
//    public Integer getExpYear() {
//        return getInteger(this.yearSpinner);
//    }
//
//    public void saveForm(View button) {
//        ((PaymentActivity)getActivity()).saveCreditCard(this);
//    }

    private Integer getInteger(Spinner spinner) {
        try {
            return Integer.parseInt(spinner.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
