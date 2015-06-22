package com.strendent.tutorsu.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
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
import com.strendent.tutorsu.Models.MyItem;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.MyItemReader;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

/**
 * Simple activity demonstrating ClusterManager.
 */
public class Activity_Home extends ActionBarActivity implements  FragmentDrawer.FragmentDrawerListener{

    private ClusterManager<MyItem> mClusterManager;
    private GoogleMap mMap;

    private static String TAG = Activity_Home.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    FrameLayout container_body;

    private SupportMapFragment mMapFragment;


    protected int getLayoutId() {
        return R.layout.map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        container_body=(FrameLayout)findViewById(R.id.container_body);
        mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map));


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
//        displayView(0);

        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        showCluster();
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

                // set the toolbar title
                getSupportActionBar().setTitle(getString(R.string.nav_item_home));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));
                mMapFragment.getView().setVisibility(View.VISIBLE);
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

            mMapFragment.getView().setVisibility(View.INVISIBLE);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            container_body.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    protected void showCluster() {


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<MyItem>(this,mMap);
        mMap.setOnCameraChangeListener(mClusterManager);

        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
        mClusterManager.addItems(items);
    }
}