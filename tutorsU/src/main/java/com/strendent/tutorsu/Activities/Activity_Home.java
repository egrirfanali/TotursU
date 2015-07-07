package com.strendent.tutorsu.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.joshdholtz.sentry.Sentry;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.strendent.tutorsu.Fragment.FragmentDrawer;
import com.strendent.tutorsu.FragmentMian.Fragment_About;
import com.strendent.tutorsu.FragmentMian.Fragment_Become_A_Tutor;
import com.strendent.tutorsu.FragmentMian.Fragment_FavouriteLocations;
import com.strendent.tutorsu.FragmentMian.Fragment_MyFamily;
import com.strendent.tutorsu.FragmentMian.Fragment_Payments;
import com.strendent.tutorsu.FragmentMian.Fragment_Promotions;
import com.strendent.tutorsu.FragmentMian.Fragment_Share;
import com.strendent.tutorsu.FragmentMian.Fragment_TrustedTutors;
import com.strendent.tutorsu.FragmentMian.Fragment_Tutions;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.Constants_MixPannel;
import com.strendent.tutorsu.Utilities.Constants_Sentry;
import com.strendent.tutorsu.Utilities.Utility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Activity_Home extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = Activity_Home.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    MixpanelAPI mixpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        try {
            String params2="first_name=Mustafa&last_name=Saeed&email=mustafa.saeed@strendent.com&phone=5555555555&zipcode=90401&dob=1970-01-22&ssn=XXX-XX-2001&driver_license_number=F1112001&driver_license_state=CA";
         /*   String params2="first_name=Your"+"&"+"last_name=Name"+"&"+"email=your.name@example.com"+"&"+
                    "phone=5555555555"+"&"+"zipcode=90401"+"&"+"dob=1970-01-22"+"&"+"ssn=111-11-2001"+"&"+"driver_license_number=F1112001"+"&"+
                    "driver_license_number=F1112001"+"&"+"driver_license_state=CA";*/
          //  String chkrUrl="http://c7aea53fac8319c95d63450fbef03ce439cad1d8:@api.checkr.com/v1/candidates/";
            try {
        //        webInvokeGetRequest("https://api.checkr.com/c7aea53fac8319c95d63450fbef03ce439cad1d8","");
            //   webInvokeGetRequest(chkrUrl,params2);
             //   ParseCloud.callFunctionInBackground("checkr", null);
                ParseCloud.callFunctionInBackground("checkr",
                        new HashMap<String, Object>(), new FunctionCallback< Object >() {

                            @Override
                            public void done(Object arg0, ParseException arg1) {
                                // TODO Auto-generated method stub

                                String myInvoice = arg0.toString();

                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
            Sentry.init(this.getApplicationContext(), Constants_Sentry.sentryDns);
            mixpanel= MixpanelAPI.getInstance(this, Constants_MixPannel.mixpannelProjectToken);


            JSONObject props = new JSONObject();
            props.put("Gender", "Male");
            props.put("Logged in", true);

            mixpanel.track(Constants_MixPannel.MAIN_ACTIVITY, props);
            try {
                //TODO: It's a custom exception generated to test Sentry
                String expectionMessage= "abcdefghijkl";
                int a =  Integer.parseInt(expectionMessage);
            } catch (Exception e) {
                e.printStackTrace();
                //Map to send data to sentry
                HashMap<String,String> sentryMap=new HashMap<>();
                sentryMap.put("UserName", "Nouman Ghaffar");
                sentryMap.put("Device  ", Build.DEVICE);
                sentryMap.put("Device Model ", android.os.Build.MODEL);
                sentryMap.put("Device Os", android.os.Build.VERSION.RELEASE);
                Utility.sendSentryLog(e, Constants_Sentry.HOME_SCREEN_ON_CREATE, sentryMap);
               /* Sentry.captureEvent(new Sentry.SentryEventBuilder().setMessage(e.toString()).
                        setCulprit(Constants_Sentry.HOME_SCREEN_ON_CREATE).setTimestamp(System.currentTimeMillis())
                        .setException(e).setExtra(sentryMap));*/

            }
        } catch (JSONException e) {

            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }
        // Setting Custom Action Bar i.e ToolBar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    // Setting Fragments
   //   getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
        try {
            //TODO: It's a custom exception generated to test Sentry
            String expectionMessage= "oioioioioi";
            int a =  Integer.parseInt(expectionMessage);
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String,String> sentryMap=new HashMap<>();
            sentryMap.put("UserName", "Nouman Ghaffar");
            sentryMap.put("Device  ", Build.DEVICE);
            sentryMap.put("Device Model ", android.os.Build.MODEL);
            sentryMap.put("Device Os", android.os.Build.VERSION.RELEASE);
            Utility.sendSentryLog(e,"Sample Expection",sentryMap);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fav_location_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        mixpanel.track(Constants_MixPannel.MENU_OPEN);

        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
               // Toast.makeText(Activity_Home.this, "1 Options Clicked", Toast.LENGTH_SHORT).show();
                drawerFragment.isHidden();
                break;

            case 1:
                fragment = new Fragment_TrustedTutors();
                title = getString(R.string.trested_tutors_title);
                mixpanel.track(Constants_MixPannel.TRUSTED_TUTORS);

                break;

            case 2:
                fragment = new Fragment_MyFamily();
                title = getString(R.string.my_family_title);
                mixpanel.track(Constants_MixPannel.MY_FAMILY);
                break;

            case 3:
                fragment = new Fragment_FavouriteLocations();
                title = getString(R.string.favorite_locations_title);
                mixpanel.track(Constants_MixPannel.FAVOURITE_LOCATIONS);


                break;
            case 4:
                fragment = new Fragment_Tutions();
                title = getString(R.string.tutions_title);
                mixpanel.track(Constants_MixPannel.TUTOINS_OPENED);
                break;

            case 5:
                Intent signupIntent = new Intent(this, Activity_SignUp.class);
                startActivity(signupIntent);
                mixpanel.track(Constants_MixPannel.PROFILE);
               /* fragment = new Fragment_Profile();
                title = getString(R.string.profile_title);*/
                break;


            case 6:
                fragment = new Fragment_Payments();
                title = getString(R.string.payments_title);
                mixpanel.track(Constants_MixPannel.PAYMENTS);

                break;
            case 7:
                fragment = new Fragment_Share();
                title = getString(R.string.share_title);
                mixpanel.track(Constants_MixPannel.SHARE);
                break;
            case 8:
                fragment = new Fragment_Promotions();
                title = getString(R.string.promotions_title);
                mixpanel.track(Constants_MixPannel.PROMOTIONS);
                break;
            case 9:
                fragment = new Fragment_About();
                title = getString(R.string.about_title);
                mixpanel.track(Constants_MixPannel.ABOUT);
                break;
            case 10:
                fragment = new Fragment_Become_A_Tutor();
                title = getString(R.string.become_a_tutor_title);

                break;


            default:
                break;
        }


        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public JSONObject webInvokeGetRequest(String webServiceUrl, String methodName) throws Exception {
        HttpEntity entity = null;
        String ret;
        HttpResponse response = null;
        HttpGet httpGet = null;
        JSONObject myObject = null;
        DefaultHttpClient httpClient;
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 10000);
        httpClient = new DefaultHttpClient(params);
        try {
            HttpClient client = new DefaultHttpClient();
            httpGet = new HttpGet(webServiceUrl + methodName);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                    CookiePolicy.RFC_2109);
            httpGet.setHeader(
                    "Accept",
                    "application/json,text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
            response = client.execute(httpGet);
            entity = response.getEntity();

            ret = EntityUtils.toString(entity);

        //    myObject = new JSONObject(ret);
            System.out.println(ret);

        } catch (Exception e) {
            throw e;
        }
        return myObject;
    }
}
