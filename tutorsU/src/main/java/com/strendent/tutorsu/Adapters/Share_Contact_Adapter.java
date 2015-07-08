package com.strendent.tutorsu.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.strendent.tutorsu.Models.Model_Contacts_Model;
import com.strendent.tutorsu.Models.PaymentItem;
import com.strendent.tutorsu.R;

import java.util.List;

/**
 * Created by user on 6/30/2015.
 */
public class Share_Contact_Adapter extends ArrayAdapter<Model_Contacts_Model>
{

    private Context mContext;
    List<Model_Contacts_Model> contactsModelList;

    public Share_Contact_Adapter(Context context,List<Model_Contacts_Model> contactList) {
        super(context, R.layout.fragment_main_fav_location_row_item, contactList);
        mContext=context;
        contactsModelList=contactList;

    }

    public int getCount()
    {
        // return the number of records in cursor
        return contactsModelList.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position,  View view, ViewGroup parent)
    {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_share_contact_display_row_item, null);

        // move the cursor to required position


        return view;
    }


    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
