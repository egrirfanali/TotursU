package com.strendent.tutorsu.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.strendent.tutorsu.Fragment.FragmentDrawer;
import com.strendent.tutorsu.FragmentMian.Fragment_About;
import com.strendent.tutorsu.FragmentMian.Fragment_Become_A_Tutor;
import com.strendent.tutorsu.FragmentMian.Fragment_FavouriteLocations;
import com.strendent.tutorsu.FragmentMian.Fragment_MyFamily;
import com.strendent.tutorsu.FragmentMian.Fragment_Payments;
import com.strendent.tutorsu.FragmentMian.Fragment_Profile;
import com.strendent.tutorsu.FragmentMian.Fragment_Promotions;
import com.strendent.tutorsu.FragmentMian.Fragment_Share;
import com.strendent.tutorsu.FragmentMian.Fragment_TrustedTutors;
import com.strendent.tutorsu.FragmentMian.Fragment_Tutions;
import com.strendent.tutorsu.R;

public class Activity_Home extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = Activity_Home.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
      /*  FragmentManager fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });*/
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
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
        getMenuInflater().inflate(R.menu.activity__main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
			return true;
		}*/
//
//		if(id == R.id.action_search){
//			Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
//			return true;
//		}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
               // Toast.makeText(Activity_Home.this, "1 Options Clicked", Toast.LENGTH_SHORT).show();
                drawerFragment.isHidden();
                break;

            case 1:
                fragment = new Fragment_TrustedTutors();
                title = getString(R.string.trested_tutors_title);

                break;

            case 2:
                fragment = new Fragment_MyFamily();
                title = getString(R.string.my_family_title);
                break;

            case 3:
                fragment = new Fragment_FavouriteLocations();
                title = getString(R.string.favorite_locations_title);


                break;
            case 4:
                fragment = new Fragment_Tutions();
                title = getString(R.string.tutions_title);
                break;

            case 5:
                fragment = new Fragment_Profile();
                title = getString(R.string.profile_title);
                break;


            case 6:
                fragment = new Fragment_Payments();
                title = getString(R.string.payments_title);

                break;
            case 7:
                fragment = new Fragment_Share();
                title = getString(R.string.share_title);

                break;
            case 8:
                fragment = new Fragment_Promotions();
                title = getString(R.string.promotions_title);

                break;
            case 9:
                fragment = new Fragment_About();
                title = getString(R.string.about_title);

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
