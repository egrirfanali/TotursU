package com.strendent.tutorsu.FragmentsInner;

import android.app.Activity;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.strendent.tutorsu.Adapters.GooglePlacesAutocompleteAdapter;
import com.strendent.tutorsu.FragmentMian.Fragment_FavouriteLocations;
import com.strendent.tutorsu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.EditText;
public class FragmentInner_Add_Location extends Fragment  {
    View view;
    private static final String LOG_TAG = "ExampleApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    String address="";
    EditText edit_text_address_title;
    String addressTitle="";
    TextView addressTextView;
    String str;
    FrameLayout fragmentContainer;
    private static final String API_KEY = "AIzaSyAFt6QLGctaNQxUvTnFj77r4w688n1GW4I";
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


        view=inflater.inflate(R.layout.fragment_main_fav_location_add_location_layout, null);
        AutoCompleteTextView autoCompView = (AutoCompleteTextView) view.findViewById(R.id.tv_auto_complete_location);
        addressTextView = (TextView) view.findViewById(R.id.tv_address_primary);
        edit_text_address_title=(EditText) view.findViewById(R.id.edit_text_address_title);
        fragmentContainer=(FrameLayout) view.findViewById(R.id.selectionlayout);
        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(getActivity(), R.layout.fragment_inner_add_location_row_items));

        autoCompView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               str = (String) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();

                addressTextView.setText(str);
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
            addressTitle= edit_text_address_title.getText().toString();
            data.putString("title", addressTitle);
            data.putString("address", str);
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
    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:usa");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println("URL: "+url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

}
