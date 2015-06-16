package com.strendent.tutorsu.FragmentMian;

import com.strendent.tutorsu.Activities.Activity_Home;
import com.strendent.tutorsu.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

public class Fragment_FavouriteLocations extends Fragment {
    Toolbar mToolbar;

    public Fragment_FavouriteLocations() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
       /* mToolbar = (Toolbar) inflater.inflate(R.id.toolbar, container);

        setSupportActionBar(mToolbar);*/
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main_fav_location_row_item, container, false);


    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.edit_button);
        item.setVisible(true);
        getActivity().supportInvalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_button) {
            Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_LONG).show();

            return true;
        }
//
//		if(id == R.id.action_search){
//			Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
//			return true;
//		}

        return super.onOptionsItemSelected(item);
    }

}
