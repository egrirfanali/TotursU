package com.strendent.tutorsu.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.strendent.tutorsu.Models.PaymentItem;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.CardValidator;
import com.strendent.tutorsu.Utilities.Constants;
import com.strendent.tutorsu.Utilities.Utility;
import com.strendent.tutorsu.Utilities.Validator;
import com.strendent.tutorsu.dialog.ErrorDialogFragment;
import com.strendent.tutorsu.dialog.ProgressDialogFragment;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;

import java.util.Calendar;


public class Activity_CardPayment extends FragmentActivity {

    private EditText edtCardCredent;
    private EditText edtCardLastPart;
    private EditText edtExpiry;
    private EditText edtCvc;
    private  RadioButton radioButtonYes;

    private int ADD_ANOTHER=2;
    private int DONE=1;

    private ProgressDialogFragment progressFragment;

    ImageView imageViewCardType;
    // Change this to what you want... ' ', '-' etc..
    private static final char space = ' ';

    LinearLayout linearLayoutPaymentDet;

    private String current = "";
    private String ddmmyyyy = "MMYY";
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);


//        progressFragment = ProgressDialogFragment.newInstance(R.string.progressMessage);
        radioButtonYes=(RadioButton)findViewById(R.id.radioButtonYes);
        edtCardCredent= (EditText)  findViewById(R.id.edtCardCredent);
        imageViewCardType=(ImageView) findViewById(R.id.imageViewCardType);
        linearLayoutPaymentDet=(LinearLayout) findViewById(R.id.linearLayoutPaymentDet);

        edtCardCredent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().startsWith("4")) {
                    imageViewCardType.setImageResource(R.drawable.ic_visa);
                    imageViewCardType.setTag("ic_visa");
                } else if (s.toString().startsWith("5")) {
                    imageViewCardType.setImageResource(R.drawable.ic_mastercard);
                    imageViewCardType.setTag("ic_mastercard");
                } else if (s.toString().startsWith("35")) {
                    imageViewCardType.setImageResource(R.drawable.ic_jcb);
                    imageViewCardType.setTag("ic_jcb");
                } else if (s.toString().startsWith("305") || s.toString().startsWith("385")) {
                    imageViewCardType.setImageResource(R.drawable.ic_diners);
                    imageViewCardType.setTag("ic_diners");
                } else if (s.toString().startsWith("6")) {
                    imageViewCardType.setImageResource(R.drawable.ic_discover);
                    imageViewCardType.setTag("ic_discover");
                } else if (s.toString().startsWith("37")) {
                    imageViewCardType.setImageResource(R.drawable.ic_amex);
                    imageViewCardType.setTag("ic_amex");
                } else if (s.toString().startsWith("35")) {
                    imageViewCardType.setImageResource(R.drawable.ic_jcb);
                    imageViewCardType.setTag("ic_jcb");
                } else {
                    imageViewCardType.setImageResource(R.drawable.ic_placeholder);
                    imageViewCardType.setTag("ic_placeholder");
                }

                if (s.toString().length() == 19) {

//                    s.toString().replaceAll("\\s+","");
                    if (CardValidator.validate(s.toString().replaceAll("\\s+", ""))) {

                        edtCardCredent.setTextColor(Color.BLACK);

                        addViewsToPaymentLayout();



                        edtExpiry.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().equals(current)) {
                                    String clean = s.toString().replaceAll("[^\\d.]", "");
                                    String cleanC = current.replaceAll("[^\\d.]", "");

                                    int cl = clean.length();
                                    int sel = cl;
                                    for (int i = 2; i <= cl && i < 6; i += 2) {
                                        sel++;
                                    }
                                    //Fix for pressing delete next to a forward slash
                                    if (clean.equals(cleanC)) sel--;

                                    if (clean.length() < 4) {
                                        clean = clean + ddmmyyyy.substring(clean.length());
                                    } else {
                                        //This part makes sure that when we finish entering numbers
                                        //the date is correct, fixing it otherwise
                                        int mon = Integer.parseInt(clean.substring(0, 2));
                                        int year = Integer.parseInt(clean.substring(2, 4));
//                                        int year = Integer.parseInt(clean.substring(4, 8));

                                        if (mon > 12) mon = 12;
                                        cal.set(Calendar.MONTH, mon - 1);

                                        int y = Calendar.getInstance().get(Calendar.YEAR);

                                        Log.d("year", String.valueOf(y));

                                        String y1 = String.valueOf(y);
                                        String y2 = y1.substring(2);
                                        Log.d("year part", String.valueOf(y2));
//                                        if(Validator.isInteger(String.valueOf(year)) && String.valueOf(year).equals("")) {
                                        year = (year < Integer.valueOf(y2)) ? Integer.valueOf(y2) : (year > (Integer.valueOf(y2) + 15)) ? Integer.valueOf(y2) : year;
//                                        }
                                        cal.set(Calendar.YEAR, year);
                                        // ^ first set year for the line below to work correctly
                                        //with leap years - otherwise, date e.g. 29/02/2012
                                        //would be automatically corrected to 28/02/2012

//                                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                                        clean = String.format("%02d%02d", mon, year);
                                    }

                                    clean = String.format("%s/%s", clean.substring(0, 2),
                                            clean.substring(2, 4));

                                    sel = sel < 0 ? 0 : sel;
                                    current = clean;
                                    edtExpiry.setText(current);
                                    edtExpiry.setSelection(sel < current.length() ? sel : current.length());
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });


                    } else {

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


    // Done Button OnClick
    public void onDone(View view){

        if(Validator.etReqiurd(edtExpiry) && Validator.etReqiurd(edtCardCredent)) {
            String[] str = edtExpiry.getText().toString().split("/");
            saveCreditCard(edtCardCredent.getText().toString(), str[0], str[1], edtCvc.getText().toString(), DONE);
        }else{
            handleError(R.string.validationErrors, "Enter required fields");
        }
    }

    // AddAnother Button OnClick
    public void addAnother(View view){

        String[] str = edtExpiry.getText().toString().split("/");
        saveCreditCard(edtCardCredent.getText().toString(), str[0], str[1], edtCvc.getText().toString(),ADD_ANOTHER);
    }


    Card card;
    public void saveCreditCard(String cardNo,String expMonth,String expYear,String cvc, final int addOrDoneOption) {

        card = new Card(cardNo,Integer.parseInt(expMonth),Integer.parseInt(expYear),cvc);

        boolean validation = card.validateCard();
        if (validation) {
            startProgress();
            new Stripe().createToken(
                    card,
                    Constants.PUBLISHABLE_KEY,
                    new TokenCallback() {
                        public void onSuccess(com.stripe.android.model.Token token) {
//                            getTokenList().addToList(token);
                            Toast.makeText(getApplicationContext(),token.getId(),Toast.LENGTH_LONG).show();
                            finishProgress();

                           /* Drawable drawable = imageViewCardType.getDrawable();
                            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                            Bundle newBundle = new Bundle();
                            newBundle.putString("CARD_NUMNER", edtCardCredent.getText().toString());
                            newBundle.putBoolean("IS_PRIMARY", true);
                            //Trying to pass a drawable from one activity to another
                            newBundle.putParcelable("BITMAP", bitmap);*/

                            // Getting card type image and create PaymentItem object. Saving PaymentItem in Constants.paymentItemsList
                            // which is later on will be used in Paymentcardlis

                            Drawable drawable = imageViewCardType.getDrawable();
                            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                            byte[] byteArray=Utility.convertBitmapToByteArray(bitmap);


                            PaymentItem paymentItem = new PaymentItem(edtCardCredent.getText().toString(),radioButtonYes.isChecked(),
                                    edtCvc.getText().toString(),edtExpiry.getText().toString(),byteArray);
                            Constants.paymentItemsList.add(paymentItem);

                            //Check whether it's Add Another card option or Done option
                            if(addOrDoneOption==DONE) {
                                Intent intentActivityPaymentLi = new Intent(getApplicationContext(), Activity_PaymentCardList.class);
                                // intentActivityPaymentLi.putExtras(newBundle);
                                startActivity(intentActivityPaymentLi);
                            }else{
                                removeViewsFromPaymentLayout();
                            }

                        }
                        public void onError(Exception error) {
                            handleError(R.string.validationErrors,error.getLocalizedMessage());
                            finishProgress();
                        }
                    });
        } else if (!card.validateNumber()) {
            handleError(R.string.validationErrors,"The card number that you entered is invalid");
        } else if (!card.validateExpiryDate()) {
            handleError(R.string.validationErrors,"The expiration date that you entered is invalid");
        } else if (!card.validateCVC()) {
            handleError(R.string.validationErrors,"The CVC code that you entered is invalid");
        } else {
            handleError(R.string.validationErrors,"The card details that you entered are invalid");
        }
    }

    private void startProgress() {
//        progressFragment.show(getSupportFragmentManager(), "progress");
    }

    private void finishProgress() {
//        progressFragment.dismiss();
    }

    private void handleError(int titleId,String error) {
        android.support.v4.app.DialogFragment fragment = ErrorDialogFragment.newInstance(titleId, error);
        fragment.show(getSupportFragmentManager(), "error");
    }

    private void addViewsToPaymentLayout(){

        View view= getLayoutInflater().inflate(R.layout.payment_placeholder,null);
        edtCardLastPart=(EditText)view.findViewById(R.id.edtCardLastPart);
        edtExpiry=(EditText)view.findViewById(R.id.edtExpiry);
        edtCvc=(EditText)view.findViewById(R.id.edtCvc);

        edtCardLastPart.setText(edtCardCredent.getText().toString().substring(15));

//        edtCardCredent.setVisibility(View.GONE);

        Animation nextAnim = AnimationUtils.loadAnimation(
                Activity_CardPayment.this, R.anim.next);
        // Start the animation
//        linearLayoutPaymentDet.setAnimation(nextAnim);

        linearLayoutPaymentDet.removeView(edtCardCredent);
        view.setAnimation(nextAnim);
        linearLayoutPaymentDet.addView(view);
    }

    private void removeViewsFromPaymentLayout(){

        View view= getLayoutInflater().inflate(R.layout.payment_placeholder,null);

        edtCardLastPart=(EditText)view.findViewById(R.id.edtCardLastPart);
        edtExpiry=(EditText)view.findViewById(R.id.edtExpiry);
        edtCvc=(EditText)view.findViewById(R.id.edtCvc);
        edtCardLastPart.setText("");
        edtExpiry.setText("");
        edtCvc.setText("");

//        view.setVisibility(View.GONE);
        linearLayoutPaymentDet.removeAllViews();
        edtCardCredent.setText("");

        edtCardCredent.setVisibility(View.VISIBLE);




        Animation nextAnim = AnimationUtils.loadAnimation(
                Activity_CardPayment.this, R.anim.next);
        Animation previousAnim = AnimationUtils.loadAnimation(
                Activity_CardPayment.this, R.anim.previous);

//        view.setAnimation(previousAnim);

//        linearLayoutPaymentDet.removeView(view);

        // Start the animation
        edtCardCredent.setAnimation(nextAnim);

        linearLayoutPaymentDet.addView(imageViewCardType);
        linearLayoutPaymentDet.addView(edtCardCredent);
    }



    private Integer getInteger(Spinner spinner) {
        try {
            return Integer.parseInt(spinner.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
