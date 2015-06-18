package com.strendent.tutorsu.FragmentMian;

import com.strendent.tutorsu.Activities.Activity_Home;
import com.strendent.tutorsu.Adapters.Fragment_main_Fav_Location_Adapter;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Add_Location;
import com.strendent.tutorsu.Models.Fav_Location_model;
import com.strendent.tutorsu.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

public class Fragment_FavouriteLocations extends Fragment {
    Toolbar mToolbar;
    View view;
    ListView locationListView;
    List<Fav_Location_model> locationsList = new ArrayList<Fav_Location_model>();
    FrameLayout fragmentContainer;
    public Fragment_FavouriteLocations() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            String addressTitle = bundle.getString("title");
            String address = bundle.getString("address");
            locationsList.add(new Fav_Location_model(addressTitle, address, ""));
        }
        initList();
        view = inflater.inflate(R.layout.fragment_main_fav_location_main_layout, null);
        fragmentContainer=(FrameLayout) view.findViewById(R.id.fragment_container);
        fragmentContainer.setVisibility(View.GONE);
        initView();

        Fragment_main_Fav_Location_Adapter favLocationAdapter = new Fragment_main_Fav_Location_Adapter(locationsList, getActivity());
        locationListView.setAdapter(favLocationAdapter);
        return view;


    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        MenuItem item2 = menu.findItem(R.id.edit_button);
        item2.setVisible(true);
        MenuItem item = menu.findItem(R.id.done_button);
        item.setVisible(false);
        getActivity().supportInvalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit_button) {
            Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
            FragmentInner_Add_Location fragment = new FragmentInner_Add_Location();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            fragmentContainer.setVisibility(View.VISIBLE);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        if (view != null) {
            locationListView = (ListView) view.findViewById(R.id.listView_locations);
        }

    }

    private void initList() {
        // We populate the planets

        locationsList.add(new Fav_Location_model("Home", "Abcdef", "Abcdefgh"));
        locationsList.add(new Fav_Location_model("Work", "12345", "345646465465564"));
        locationsList.add(new Fav_Location_model("Office", "oooooo", "aaaaaaa"));
        locationsList.add(new Fav_Location_model("Sports", "zzzzzzzzz", "yyyyyyyyyyyy"));
    }
}
