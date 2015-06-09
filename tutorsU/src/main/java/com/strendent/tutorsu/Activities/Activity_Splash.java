package com.strendent.tutorsu.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.strendent.tutorsu.R;

public class Activity_Splash extends Activity {

	private boolean _active = true;
	private int _splashTime = 5000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity__splash);
       // authProgressDialog=ProgressDialog.show(Splash.this,getResources().getString(R.string.laoding) , getResources().getString(R.string.please_wait), false, false);
		 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//		 this.getWindow().set
//		 new LovLaoding().execute();
		 final Intent intent=new Intent(this,Activity_Main.class);
	        
	       Thread splashThread=new Thread()
	        {
	        	 @Override
	             public void run() {
	                 try {
//	                     startActivity(intent);
	                     int waited = 0;
	                     while(_active && (waited < _splashTime)) {
	                         sleep(100);
	                         if(_active) {
	                             waited += 100;
	                         }
	                     }
	                 } catch(InterruptedException e) {
	                     // do nothing
	                 } finally {
	                     finish();
	                     startActivity(intent);
	                     //stop();
	                 }
	             }
	        	
	        };
        
        
	        splashThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__splash, menu);
		return true;
	}

}
