package com.strendent.tutorsu.Utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import com.strendent.tutorsu.Activities.Activity_DefaultExpectionViewerActivity;

public class ExceptionHandler implements
		Thread.UncaughtExceptionHandler {
	private final Activity myContext;
	private final String LINE_SEPARATOR = "\n";
	private String expcetionPoint="";

	public ExceptionHandler(Activity context , String exceptionMessage ) {
		myContext = context;
		expcetionPoint=exceptionMessage;
	}

	public void uncaughtException(Thread thread, Throwable exception) {

		//Map to send data to sentry

		HashMap<String,String> sentryDetailsMap=Utility.sentryMap;

		//	sentryMap.put("UserName", "Nouman Ghaffar");

		sentryDetailsMap.put("Device  ", Build.DEVICE);
		sentryDetailsMap.put("Device Model ", android.os.Build.MODEL);
		sentryDetailsMap.put("Brand", Build.BRAND);
		StringBuilder errorReport = new StringBuilder();
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		errorReport.append(stackTrace.toString());


			//Coverting Throwable Expection to Expection

		Exception caughtedExpection = new Exception(stackTrace.toString());

		//Sending Error Log to Sentry

		Utility.sendSentryLog(caughtedExpection, exception.getMessage(),expcetionPoint, sentryDetailsMap);

		// Starting Activity To show Error

		Intent intent = new Intent(myContext, Activity_DefaultExpectionViewerActivity.class);
		myContext.startActivity(intent);
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
	}

}