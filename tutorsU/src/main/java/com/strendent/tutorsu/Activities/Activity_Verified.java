package com.strendent.tutorsu.Activities;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.R.layout;
import com.strendent.tutorsu.R.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Activity_Verified extends Activity {

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=Activity_Verified.this;
		// remove title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity__verified);
	}



//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity__verified, menu);
//		return true;
//	}

	public void onClickSkip(View view){
		Intent intent=new Intent(getApplicationContext(), Activity_Home.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void onClickProceed(View view){

		Toast.makeText(context, "In onproceed", Toast.LENGTH_LONG).show();

		Intent intent=new Intent(getApplicationContext(), Activity_PaymentOption.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		Intent intent=new Intent(getApplicationContext(), Activity_Home.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}



}
