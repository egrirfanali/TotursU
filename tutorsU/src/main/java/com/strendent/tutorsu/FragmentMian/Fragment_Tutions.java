package com.strendent.tutorsu.FragmentMian;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_BookMark;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_MyTrusted;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Recommended;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Tutions_History;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Tutions_Scheduled;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;

public class Fragment_Tutions extends FragmentActivity {

	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// try {
		setContentView(R.layout.fragment_my_trusted_tab_layout);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		View scheduledTabStyle = LayoutInflater.from(this).inflate(R.layout.my_trusted_tab_style_layout, null);
		View historyTabStyle = LayoutInflater.from(this).inflate(R.layout.my_recommended_tab_style_layout, null);

		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(scheduledTabStyle), FragmentInner_Tutions_Scheduled.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(historyTabStyle), FragmentInner_Tutions_History.class, null);
	}
}