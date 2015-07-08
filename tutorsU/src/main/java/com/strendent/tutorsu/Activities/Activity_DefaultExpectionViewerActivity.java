package com.strendent.tutorsu.Activities;

import android.app.Activity;
import android.os.Bundle;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.Constants_Sentry;
import com.strendent.tutorsu.Utilities.ExceptionHandler;

/**
 * Created by user on 7/8/2015.
 */
public class Activity_DefaultExpectionViewerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this, Constants_Sentry.HOME_SCREEN));
        setContentView(R.layout.activity_default_expection_viewer_activity);
    }
}
