package com.strendent.tutorsu.FragmentMian;

import com.facebook.android.Facebook;
import com.strendent.tutorsu.Activities.Activity_Share_Contacts_display;
import com.strendent.tutorsu.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
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
    int PICK_CONTACT=1;
    Facebook mfacebook;
    public Fragment_Share() {
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragement_main_share_layout, container, false);
            initViews();

          //  FacebookSdk.sdkInitialize(getApplicationContext());
            btnShareViaEmail.setOnClickListener(btnEmailClickListiner);
            btnFacebookContacts.setOnClickListener(btnFacebookContactsClickListiner);

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
    View.OnClickListener btnFacebookContactsClickListiner = new View.OnClickListener() {
        public void onClick(View v) {
            String appLinkUrl, previewImageUrl;

            appLinkUrl = "https://fb.me/493857950766025";
            previewImageUrl = "http://2.bp.blogspot.com/-99shOruuadw/VQsG2T233sI/AAAAAAAAEi0/noFTxUBh_rg/s1600/appscripts.png";

           /* AppInviteContent content = new AppInviteContent.Builder()
                    .setApplinkUrl(appLinkUrl)
                    .setPreviewImageUrl(previewImageUrl)
                    .build();
            AppInviteDialog.show(facebook.this, content);*/



                  }
    };



    View.OnClickListener btnPhoneContactsClickListiner=new View.OnClickListener() {
        public void onClick(View v)
        {

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setType("vnd.android-dir/mms-sms");
            sendIntent.putExtra("sms_body", getResources().getString(R.string.share_message));
            startActivity(sendIntent);


        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }
}