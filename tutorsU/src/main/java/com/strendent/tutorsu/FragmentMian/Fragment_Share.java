package com.strendent.tutorsu.FragmentMian;

import com.strendent.tutorsu.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment_Share extends Fragment {

    private View mView;
    Button btnPhoneContacts, btnGmailContacts, btnFacebookContacts, btnTwitterContacts, btnShareViaEmail;

    public Fragment_Share() {
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragement_main_share_layout, container, false);
            initViews();
            btnShareViaEmail.setOnClickListener(btnEmailClickListiner);
            btnPhoneContacts.setOnClickListener(btnPhoneContactsClickListiner);
        }
        return mView;
    }

    public void initViews() {
        btnPhoneContacts = (Button) mView.findViewById(R.id.btn_phoneContacts);
        btnGmailContacts = (Button) mView.findViewById(R.id.btn_gmailContacts);
        btnFacebookContacts = (Button) mView.findViewById(R.id.btn_facebookContacts);
        btnTwitterContacts = (Button) mView.findViewById(R.id.btn_twitterContacts);
        btnShareViaEmail = (Button) mView.findViewById(R.id.btn_share_viaEmail);
    }

    View.OnClickListener btnEmailClickListiner = new View.OnClickListener() {
        public void onClick(View v) {
            Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
            // prompts email clients only
            email.setType("message/rfc822");


            try {
                // the user can choose the email client
                startActivity(Intent.createChooser(email, "Choose an email client from..."));

            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "No email client installed.",
                        Toast.LENGTH_LONG).show();
            }


        }
    };
    View.OnClickListener btnPhoneContactsClickListiner=new View.OnClickListener() {
        public void onClick(View v)
        {
            Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(intent, 1);
        }
    };
}