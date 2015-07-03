package com.strendent.tutorsu.Activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;

import com.strendent.tutorsu.Adapters.Share_Contact_Adapter;
import com.strendent.tutorsu.Models.Fav_Location_model;
import com.strendent.tutorsu.Models.Model_Contacts_Model;
import com.strendent.tutorsu.R;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/29/2015.
 */
public class Activity_Share_Contacts_display extends Activity implements AdapterViewCompat.OnItemClickListener {
    List<Model_Contacts_Model> contactsModelList = new ArrayList<Model_Contacts_Model>();
    ListView contactListView;

//    CustomContactAdapter customContactAdapter ;
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_share_contact_display_main_layout);
    contactListView=(ListView) findViewById(R.id.list_view_contacts);

/*
    Cursor mCursor = getContacts();
    startManagingCursor(mCursor);
*/
//    getAllContacts(this.getContentResolver());
//    Share_Contact_Adapter adapter = new Share_Contact_Adapter(this,contactsModelList);

}
    @Override
    public void onItemClick(AdapterViewCompat<?> adapterViewCompat, View view, int i, long l) {

    }
//    public  void getAllContacts(ContentResolver cr) {
//
//        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
//        while (phones.moveToNext())
//        {
//            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            System.out.println(".................."+phoneNumber);
//            contactsModelList.add(new Model_Contacts_Model(name,phoneNumber));
//
//        }
//
//        phones.close();
//    }
    private String getPhoneNumber(long id) {
        String phone = null;
        Cursor phonesCursor = null;
        phonesCursor = queryPhoneNumbers(id);
        if (phonesCursor == null || phonesCursor.getCount() == 0) {
            // No valid number
            return null;
        } else if (phonesCursor.getCount() == 1) {
            // only one number, call it.
            phone = phonesCursor.getString(phonesCursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        } else {
            phonesCursor.moveToPosition(-1);
            while (phonesCursor.moveToNext()) {

                // Found super primary, call it.
                phone = phonesCursor.getString(phonesCursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                break;

            }
        }

        return phone;
    }


    private Cursor queryPhoneNumbers(long contactId) {
        ContentResolver cr = getContentResolver();
        Uri baseUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                contactId);
        Uri dataUri = Uri.withAppendedPath(baseUri,
                ContactsContract.Contacts.Data.CONTENT_DIRECTORY);

        Cursor c = cr.query(dataUri, new String[] { ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.IS_SUPER_PRIMARY, ContactsContract.RawContacts.ACCOUNT_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE,
                        ContactsContract.CommonDataKinds.Phone.LABEL }, ContactsContract.Data.MIMETYPE + "=?",
                new String[] { ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE }, null);
        if (c != null && c.moveToFirst()) {
            return c;
        }
        return null;
    }

}
