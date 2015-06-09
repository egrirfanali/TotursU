package com.strendent.tutorsu.Activities;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.parse.ParseCloud;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.TutorsUApplication;

public class Activity_SignUp extends Activity {

	private static final int RESULT_LOAD_IMAGE = 1;
	private static final int RESULT_WALLPAPER_IMAGE=2;
	private ProfilePictureView userProfilePictureView;
//	private TextView userNameView;
	private TextView tvAddWallpaper;
//	private TextView userGenderView;
//	private TextView userEmailView;
	private RelativeLayout relLayoutWallpaper;


	private ImageView imageViewProfile;
	private EditText edtFirstandLastName;
	private EditText edtEmail;
	private EditText edtPassword;
	private EditText edtPhoneNo;
	private EditText edtAddress;
	private ImageButton imageButtonAddress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		setContentView(R.layout.activity_profile);
		initViews();

		Intent intent=getIntent();
		
		// check whether its Fb sign up call or simple sign up
		if(intent.hasExtra("CLICK_FB")){
			// Fetch Facebook user info if the session is active
			ParseFacebookUtils.initialize(getString(R.string.app_id));
			Session session = ParseFacebookUtils.getSession();
			if (session != null && session.isOpened()) {
				makeMeRequest();
			}
			
		}else if(intent.hasExtra("CLICK_SIGNUP")){
			
			if(intent!=null && intent.getStringExtra("EMAIL")!=null && intent.getStringExtra("PASSWORD")!=null){
				edtEmail.setText(intent.getStringExtra("EMAIL"));
				edtPassword.setText(intent.getStringExtra("PASSWORD"));
			}
			
		}

	}


	public void onClickAddWallpaper(View view){
		Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, RESULT_WALLPAPER_IMAGE);
	}

	private void initViews(){

		imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
		edtFirstandLastName = (EditText) findViewById(R.id.edtFirstandLastName);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtPassword = (EditText) findViewById(R.id.edtPassword);
		edtPhoneNo = (EditText) findViewById(R.id.edtPhoneNo);
		imageButtonAddress = (ImageButton) findViewById(R.id.imageButtonAddress);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		relLayoutWallpaper = (RelativeLayout) findViewById(R.id.relLayoutWallpaper);
		tvAddWallpaper =(TextView) findViewById(R.id.tvAddWallpaper);
	}


	
	private void makeMeRequest() {
		Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
				new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				if (user != null) {
					// Create a JSON object to hold the profile info
					JSONObject userProfile = new JSONObject();
					try {
						// Populate the JSON object
						
						userProfile.put("facebookId", user.getId());
						userProfile.put("name", user.getName());
						if (user.getProperty("gender") != null) {
							userProfile.put("gender", user.getProperty("gender"));
						}
						if (user.getProperty("email") != null) {
							userProfile.put("email", user.getProperty("email"));
						}

						// Save the user profile info in a user property
						ParseUser currentUser = ParseUser.getCurrentUser();
						currentUser.put("profile", userProfile);
						
						/* For currentUser set currentUser.setUsername(user.getName()) and currentUser.setPassword("ba123!@#"); 
						 * otherwise it will throw exception. Parse needs these two fields for creating ParseUser.*/
						currentUser.setUsername(user.getName());
						currentUser.setPassword(user.getId());
						currentUser.put("facebookId", user.getId());
						currentUser.put("name", user.getName());
						currentUser.put("gender", user.getProperty("gender"));
						currentUser.put("email", user.getProperty("email"));
						currentUser.put("authData", userProfile);
//						

						
						/**
						 *calling signUpInBackground method for the current user instead of saveInBackGround
						 */
						if(currentUser!=null){
							currentUser.signUpInBackground();
						}
						//              currentUser.saveInBackground();

						// Show the user info
						updateViewsWithProfileInfo();
					} catch (Exception e) {
						Log.d(TutorsUApplication.TAG, "Error parsing returned user data. " + e);
					}

				} else if (response.getError() != null) {
					if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY) || 
							(response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
						Log.d(TutorsUApplication.TAG, "The facebook session was invalidated." + response.getError());
						logout();
					} else {
						Log.d(TutorsUApplication.TAG, 
								"Some other error: " + response.getError());
					}
				}
			}
		}
				);
		request.executeAsync();
	}

	
//	@Override
//	public void onResume() {
//		super.onResume();
//
//		ParseUser currentUser = ParseUser.getCurrentUser();
//		if (currentUser != null) {
//			// Check if the user is currently logged
//			// and show any cached content
//			updateViewsWithProfileInfo();
//		} else {
//			// If the user is not logged in, go to the
//			// activity showing the login view.
//			startLoginActivity();
//		}
//	}
	private void logout() {
		// Log the user out
		ParseUser.logOut();

		// Go to the login view
		startLoginActivity();
	}

	private void startLoginActivity() {
		Intent intent = new Intent(this, Activity_Main.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	public void addAddress(View v){

		edtAddress.setVisibility(View.VISIBLE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_sign_up, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if(item.getItemId()==R.id.done){

			if(imageViewProfile!=null && edtFirstandLastName!=null && edtEmail!=null && edtPassword!=null
					&& edtPhoneNo!=null && !edtFirstandLastName.getText().toString().equals("") && !edtEmail.getText().toString().equals("")
					&& !edtPassword.getText().toString().equals("")&& !edtPhoneNo.getText().toString().equals("")
					){

				Map<String, String> map=new HashMap<String, String>();
				map.put("phoneNumber", edtPhoneNo.getText().toString());

				ParseCloud.callFunctionInBackground("sendCode", map);

				Intent intent=new Intent(getApplicationContext(), Activity_VerifyCode.class);
				intent.putExtra("PHONE_NUMBER", edtPhoneNo.getText().toString());
				startActivity(intent);


			}else{
				Toast.makeText(Activity_SignUp.this, getResources().getString(R.string.please_reqiured_fields), 
						Toast.LENGTH_LONG).show();
			}

		}

		return super.onOptionsItemSelected(item);
	}

	public void addPhoto(View v){
		Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, RESULT_LOAD_IMAGE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			if(requestCode == RESULT_LOAD_IMAGE){
				imageViewProfile.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			}else if(requestCode == RESULT_WALLPAPER_IMAGE){
				tvAddWallpaper.setVisibility(View.INVISIBLE);
				relLayoutWallpaper.setBackground(new BitmapDrawable(getResources(),BitmapFactory.decodeFile(picturePath)));
			}
		}
	}


	private void updateViewsWithProfileInfo() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser.has("profile")) {
			JSONObject userProfile = currentUser.getJSONObject("profile");
			try {

//				if (userProfile.has("facebookId")) {
//					userProfilePictureView.setProfileId(userProfile.getString("facebookId"));
//				} else {
//					// Show the default, blank user profile picture
//					userProfilePictureView.setProfileId(null);
//				}

				if (userProfile.has("name")) {
					edtFirstandLastName.setText(userProfile.getString("name"));
				} else {
					edtFirstandLastName.setText("");
				}

				if (userProfile.has("email")) {
					edtEmail.setText(userProfile.getString("email"));
				} else {
					edtEmail.setText("");
				}
				if (userProfile.has("phone")) {
					edtPhoneNo.setText(userProfile.getString("phone"));
				} else {
					edtPhoneNo.setText("");
				}

			} catch (JSONException e) {
				Log.d(TutorsUApplication.TAG, "Error parsing saved user data.");
			}
		}
	}

}
