package com.strendent.tutorsu.FragmentMian;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_BookMark;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_MyTrusted;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Recommended;

public class Fragment_TrustedTutors extends FragmentActivity {

	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// try {
		setContentView(R.layout.fragment_my_trusted_tab_layout);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		View myTrustedTabStyleViwe=LayoutInflater.from(this).inflate(R.layout.my_trusted_tab_style_layout,null);
		View recommendedTabStyleView=LayoutInflater.from(this).inflate(R.layout.my_recommended_tab_style_layout,null);
		View bookmarkTabStyleView=LayoutInflater.from(this).inflate(R.layout.my_bookmark_tab_style_layout,null);
   
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(myTrustedTabStyleViwe), FragmentInner_MyTrusted.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(recommendedTabStyleView), FragmentInner_Recommended.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(bookmarkTabStyleView), FragmentInner_BookMark.class, null);
	}
}
