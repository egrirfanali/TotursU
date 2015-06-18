package com.strendent.tutorsu.Activities;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.strendent.tutorsu.Models.PaymentItem;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.Utility;

public class Activity_Payment_Add_Information extends ActionBarActivity {

    private TextView tvCardNumber;
    private TextView tvExpiryDate;
    private TextView tvSecurityCode;
    private TextView tvIsPrimary;
    private ImageView imageViewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__information);

        //Initialize views
        initializeViews();

        /**
         * Getting Payment information from previous Activity
         * */
        PaymentItem paymentItem= (PaymentItem) getIntent().getSerializableExtra("PAYMENT_ITEM");

        /**
         * Showing Payment information
         * */
        populteViews(paymentItem);

    }

    /**
     * Initialize all views
     * */
    private void initializeViews(){
        tvCardNumber=(TextView)findViewById(R.id.tvCardNumber);
        tvExpiryDate=(TextView)findViewById(R.id.tvExpiryDate);
        tvSecurityCode=(TextView)findViewById(R.id.tvSecurityCode);
        tvIsPrimary=(TextView)findViewById(R.id.tvIsPrimary);
        imageViewCard=(ImageView)findViewById(R.id.imageViewCard);

    }

    /**
     * Populating all views
     * */
    private void populteViews(PaymentItem paymentItem){

        if(paymentItem!=null){

            if(paymentItem.getByteArray()!=null){

                Drawable drawable= new BitmapDrawable(getApplicationContext().getResources(),Utility.convertByteArrayToBitmap(paymentItem.getByteArray()));
                imageViewCard.setImageDrawable(drawable);
            }

            if(paymentItem.getCardNumber()!=null) {
                tvCardNumber.setText(paymentItem.getCardNumber());
            }

//            Card card=paymentItem.getToken().getCard();
//            if(card!=null){

            if(paymentItem.getSecurityCode()!=null){
                tvSecurityCode.setText(paymentItem.getSecurityCode());
            }
            if(paymentItem.getExpDateString()!=null){

                String[] str=paymentItem.getExpDateString().split("/");
                String expDate=monthInString(Integer.parseInt(str[0]))+", "+"20"+str[1];
//                Utility.dateFormatter("MM/yy", paymentItem.getExpDateString(), "MMM, yyyy");
                tvExpiryDate.setText(expDate);
            }

//            }
            if(paymentItem.isPrimaryCard()){
                tvIsPrimary.setText(getString(R.string.you_made_this_card_your_primary_card));
            }


        }

    }
    private String monthInString(int month){
        switch (month){
            case 1:
                return "January";

            case 2:
                return "February ";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September ";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";

        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__add__information, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
