package com.strendent.tutorsu.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.Settings;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.strendent.tutorsu.Adapters.Adpater_DrawerAdapter;
import com.strendent.tutorsu.Fragment.Fragment_Login;
import com.strendent.tutorsu.Fragment.Fragment_SignUp;
import com.strendent.tutorsu.Models.Model_Drawer_Items;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.TutorsUApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Main extends Activity {

	private Button btnPhoneNo;
	//	private Button btnProceed;
	private Button btnFb;
	//	private Switch switchLoginSignUp;
	private LinearLayout fragment_container;
	private Dialog progressDialog;
	private TextView tvLoginToMeetTutor;
	private Context context;

	private String[] titles;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ArrayList<Model_Drawer_Items> drawerItems;
	private TypedArray drawerIcons;
	Adpater_DrawerAdapter drawerAdapter;
	private static FragmentManager fragmentManager;
	private static FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 
		
		setContentView(R.layout.activity__main);


		/*String userID = "TheNameOrIDOfTheUserYouWant";
		String urlConstruct = "https://www.graph.facebook.com/" + "engr.irfan.ali.khan" + "/picture";
		URL url = null;*/
		/*try {
			url = new URL(urlConstruct);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


		//		setupDrawer();
//		SmartImageView myImage = (SmartImageView) this.findViewById(R.id.img);
//		myImage.setImageUrl(url.toString());

		findViewByIds();
		
		context = Activity_Main.this;

		String string = Settings.getApplicationSignature(getApplicationContext());

		

	}

	
	public void onClickFb(View view){
		ParseUser currentUser = ParseUser.getCurrentUser();
		if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
			// Go to the user info activity
			showUserDetailsActivity();
		}else{
			onFbClick();
		}
	}

	public void onLoginClick(View view){

		Fragment_Login fragment_Login = new Fragment_Login();
		addFragment(fragment_Login);
	}

	private void addFragment(Fragment fragment){

		tvLoginToMeetTutor.setVisibility(View.INVISIBLE);
		fragment_container.setVisibility(View.VISIBLE);

		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();


		fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

		if(fragment_container.isShown()){
			fragmentTransaction.replace(R.id.fragment_container, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}else{
			fragmentTransaction.add(R.id.fragment_container, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}



	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(fragmentManager!=null){
			fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}


		super.onBackPressed();
	}


	public void onSignUpClick(View view){

		Fragment_SignUp fragment_SignUp = new Fragment_SignUp();
		addFragment(fragment_SignUp);

	}

	public void onFbClick() {
		progressDialog = ProgressDialog.show(Activity_Main.this, "", "Logging in...", true);

		List<String> permissions = Arrays.asList("public_profile", "email", "user_friends");
		// NOTE: for extended permissions, like "user_about_me", your app must
		// be reviewed by the Facebook team
		// (https://developers.facebook.com/docs/facebook-login/permissions/)

		ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				progressDialog.dismiss();
				if (user == null) {
					Log.d(TutorsUApplication.TAG, "Uh oh. The user cancelled the Facebook login.");
				} else if (user.isNew()) {

					// ParseObject testObject = new ParseObject("User");
					// testObject.put("foo", "bar");
					// testObject.saveInBackground();
					Log.d(TutorsUApplication.TAG, "User signed up and logged in through Facebook!");
					showUserDetailsActivity();
				} else {

					Log.d(TutorsUApplication.TAG, "User logged in through Facebook!");
					showUserDetailsActivity();
				}
			}
		});
	}

	private void showUserDetailsActivity() {
		Intent intent = new Intent(this, Activity_SignUp.class);
		intent.putExtra("CLICK_FB", 1);
		startActivity(intent);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}


	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.activity__main, menu);
	//		return true;
	//	}

	private void findViewByIds() {
		btnPhoneNo = (Button) findViewById(R.id.btnPhoneNo);
		//		btnProceed = (Button) findViewById(R.id.btnProceed);

		btnFb = (Button) findViewById(R.id.btnFb);

		//		switchLoginSignUp = (Switch) findViewById(R.id.switchLoginSignUp);
		fragment_container = (LinearLayout) findViewById(R.id.fragment_container);
		tvLoginToMeetTutor=(TextView)findViewById(R.id.tvLoginToMeetTutor);

	}

	public void setupDrawer() {
		try {
			drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawerList = (ListView) findViewById(R.id.drawer_list);

			View header = getLayoutInflater().inflate(R.layout.drawer_header, null);
			drawerList.addHeaderView(header);
			drawerIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
			titles = getResources().getStringArray(R.array.nav_options);
			drawerItems = new ArrayList<Model_Drawer_Items>();
			drawerItems.add(new Model_Drawer_Items(titles[0], drawerIcons.getResourceId(0, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[1], drawerIcons.getResourceId(1, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[2], drawerIcons.getResourceId(2, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[3], drawerIcons.getResourceId(3, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[4], drawerIcons.getResourceId(4, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[5], drawerIcons.getResourceId(5, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[6], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[7], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[8], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[9], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[10], drawerIcons.getResourceId(6, -1)));
			drawerAdapter = new Adpater_DrawerAdapter(this, drawerItems);
			drawerList.setAdapter(drawerAdapter);
		} catch (NotFoundException e) {

			e.printStackTrace();
		}
	}

}
