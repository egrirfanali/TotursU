package com.strendent.tutorsu.Activities;

import android.animation.LayoutTransition;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.CardValidator;
import com.strendent.tutorsu.Utilities.Constants;
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


    public void saveCreditCard(String cardNo,String expMonth,String expYear,String cvc) {

        Card card = new Card(cardNo,Integer.parseInt(expMonth),Integer.parseInt(expYear),cvc);

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
                        }
                        public void onError(Exception error) {
                            handleError(error.getLocalizedMessage());
                            finishProgress();
                        }
                    });
        } else if (!card.validateNumber()) {
            handleError("The card number that you entered is invalid");
        } else if (!card.validateExpiryDate()) {
            handleError("The expiration date that you entered is invalid");
        } else if (!card.validateCVC()) {
            handleError("The CVC code that you entered is invalid");
        } else {
            handleError("The card details that you entered are invalid");
        }
    }

//    private TokenList getTokenList() {
//        return (TokenList)(getSupportFragmentManager().findFragmentById(R.id.token_list));
//    }

    private void startProgress() {
//        progressFragment.show(getSupportFragmentManager(), "progress");
    }

    private void finishProgress() {
//        progressFragment.dismiss();
    }

    private void handleError(String error) {
        android.support.v4.app.DialogFragment fragment = ErrorDialogFragment.newInstance(R.string.validationErrors, error);
        fragment.show(getSupportFragmentManager(), "error");
    }

    //    private TokenList getTokenList() {
//        return (TokenList)(getSupportFragmentManager().findFragmentById(R.id.token_list));
//    }
    private void addViewsToPaymentLayout(){

        LayoutTransition transition = new LayoutTransition();


        View view= getLayoutInflater().inflate(R.layout.payment_placeholder,null);
        edtCardLastPart=(EditText)view.findViewById(R.id.edtCardLastPart);
        edtExpiry=(EditText)view.findViewById(R.id.edtExpiry);
        edtCvc=(EditText)view.findViewById(R.id.edtCvc);

        edtCardLastPart.setText(edtCardCredent.getText().toString().substring(15));

        edtCardCredent.setVisibility(View.GONE);


        Animation previousAnim = AnimationUtils.loadAnimation(
                Activity_CardPayment.this, R.anim.previous);
        Animation nextAnim = AnimationUtils.loadAnimation(
                Activity_CardPayment.this, R.anim.next);
        //        transition.set
        // Start the animation
//        linearLayoutPaymentDet.setAnimation(nextAnim);
        view.setAnimation(nextAnim);
//        linearLayoutPaymentDet.animate()
//                .translationX(view.getWidth())
//                .alpha(1.0f);

//        linearLayoutPaymentDet.setLayoutTransition(transition);

        linearLayoutPaymentDet.addView(view);


        edtCvc.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    String[] str = edtExpiry.getText().toString().split("/");
                    saveCreditCard(edtCardCredent.getText().toString(), str[0], str[1], edtCvc.getText().toString());
                }
                return false;
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
