package com.strendent.tutorsu.FragmentsInner;

import android.os.StrictMode;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.strendent.tutorsu.Adapters.GooglePlacesCutomAdapter;
import com.strendent.tutorsu.FragmentMian.Fragment_FavouriteLocations;
import com.strendent.tutorsu.Managers.Manager_Location_Suggestions;
import com.strendent.tutorsu.R;

import java.util.ArrayList;
import android.widget.TextView;
import android.widget.EditText;

public class FragmentInner_Add_Location extends Fragment  {
    View view;

    String address="";
    ListView locationListView;

    EditText editTextAddressTitle;
    String addressTitle="";
    TextView addressTextView;
    String str;
    ArrayList<String> list;
    FrameLayout fragmentContainer;
    public FragmentInner_Add_Location() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
               setHasOptionsMenu(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        view=inflater.inflate(R.layout.fragment_main_fav_location_add_location_layout, null);
        EditText getLocationEd = (EditText) view.findViewById(R.id.tv_auto_complete_location);
        locationListView = (ListView) view.findViewById(R.id.listView_location_List);
        addressTextView = (TextView) view.findViewById(R.id.tv_address_primary);
        editTextAddressTitle =(EditText) view.findViewById(R.id.edit_text_address_title);
        fragmentContainer=(FrameLayout) view.findViewById(R.id.selectionlayout);
        getLocationEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    addressTextView.setVisibility(View.GONE);
                    editTextAddressTitle.setVisibility(View.GONE);

                    locationListView.setVisibility(View.VISIBLE);
                    Manager_Location_Suggestions mgls = new Manager_Location_Suggestions();

                    list = mgls.autocomplete(s.toString());
                    Log.d("AddLocationList",list.toString());
                    locationListView.setAdapter(new GooglePlacesCutomAdapter(list, getActivity()));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addressTextView.setVisibility(View.VISIBLE);
                editTextAddressTitle.setVisibility(View.VISIBLE);
                locationListView.setVisibility(View.GONE);
                address=list.get(position);
                addressTextView.setText(address);
		    }
        });
        return view;



    }
    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        MenuItem item2 = menu.findItem(R.id.edit_button);
        item2.setVisible(false);
        MenuItem item = menu.findItem(R.id.done_button);
        item.setVisible(true);
        getActivity().supportInvalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
          if (id == R.id.done_button) {
            Bundle data = new Bundle();
            addressTitle= editTextAddressTitle.getText().toString();
            data.putString("title", addressTitle);
            data.putString("address", address);
            Fragment_FavouriteLocations fragment = new Fragment_FavouriteLocations();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            fragment.setArguments(data);
            fragmentContainer.setVisibility(View.VISIBLE);
            transaction.replace(R.id.selectionlayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
