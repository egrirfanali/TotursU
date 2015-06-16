package com.strendent.tutorsu.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseObject;
import com.strendent.tutorsu.Adapters.AdaptorPaymentList;
import com.strendent.tutorsu.Models.PaymentItem;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.Constants;

public class Activity_PaymentCardList extends ActionBarActivity {

    ParseObject parseObject;
    PaymentItem paymentItem;
    private ListView listViewPaymentCard;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__payment_card_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        listViewPaymentCard=(ListView)findViewById(R.id.listViewPaymentCard);

        /*Bundle bundle=getIntent().getExtras();
        Bitmap bitmap=bundle.getParcelable("BITMAP");
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);

        PaymentItem paymentItem = new PaymentItem(drawable, bundle.getString("CARD_NUMNER"),bundle.getBoolean("IS_PRIMARY"));

        Constants.paymentItemsList.add(paymentItem);*/

        if(Constants.paymentItemsList!=null && Constants.paymentItemsList.size()>0) {
            listViewPaymentCard.setAdapter(new AdaptorPaymentList(Activity_PaymentCardList.this, R.layout.payment_card_list_items, Constants.paymentItemsList));
        }
//        ParseQuery<ParseObject> query= ParseQuery.getQuery("PaymentCards");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> payments, ParseException e) {
//                if (e == null) {
//
//                    for (int i=0;i<payments.size();i++){
//
//
//                        parseObject=(ParseObject)payments.get(i);
//                        paymentItem=new PaymentItem(parseObject.getString("CARD_NUMBER"),parseObject.getString("CARD_NUMBER"),parseObject.getBoolean("CARD_NUMBER"));
//
//                    }
//
//                } else {
//                    // handle Parse Exception here
//                }
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__payment_card_list, menu);
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
