package com.strendent.tutorsu.FragmentMian;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_BookMark;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_MyTrusted;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Recommended;

public class Fragment_TrustedTutors extends Fragment {

	private View mView;

	public Fragment_TrustedTutors(){
		setRetainInstance(true);
	}
	private FragmentTabHost mTabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if(mView == null){
			mView = inflater.inflate(R.layout.fragment_my_trusted_tab_layout, container, false);
			mTabHost = (FragmentTabHost) mView.findViewById(android.R.id.tabhost);
			mTabHost.setup(getActivity(),  getChildFragmentManager(), android.R.id.tabcontent);
			View myTrustedTabStyleView=LayoutInflater.from(getActivity()).inflate(R.layout.my_trusted_tab_style_layout, null);
			View recommendedTabStyleView=LayoutInflater.from(getActivity()).inflate(R.layout.my_recommended_tab_style_layout, null);
			View bookmarkTabStyleView=LayoutInflater.from(getActivity()).inflate(R.layout.my_bookmark_tab_style_layout,null);

			mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(myTrustedTabStyleView), FragmentInner_MyTrusted.class, null);
			mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(recommendedTabStyleView), FragmentInner_Recommended.class, null);
			mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(bookmarkTabStyleView), FragmentInner_BookMark.class, null);

		}else{
			((ViewGroup) mView.getParent()).removeView(mView);
		}

		return mView;
	} }



