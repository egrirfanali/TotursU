package com.strendent.tutorsu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import android.widget.Toast;
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
import com.strendent.tutorsu.Utilities.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_Home extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = Activity_Home.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    MixpanelAPI mixpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);


        try {





            // Setting User Properties on MIX PANNEL
            mixpanel= MixpanelAPI.getInstance(this, Constants_MixPannel.mixpannelProjectToken);


            JSONObject props = new JSONObject();
            props.put("Gender", "Male");
            props.put("Logged in", true);
            mixpanel.track(Constants_MixPannel.MAIN_ACTIVITY, props);
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
}
