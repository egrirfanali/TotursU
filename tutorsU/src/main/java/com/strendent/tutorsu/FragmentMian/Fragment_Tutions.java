package com.strendent.tutorsu.FragmentMian;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_BookMark;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_MyTrusted;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Recommended;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Tutions_History;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Tutions_Scheduled;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Tutions extends Fragment {

	private View mView;

	public Fragment_Tutions(){
		setRetainInstance(true);
	}
	private FragmentTabHost mTabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_my_trusted_tab_layout, container, false);

			mTabHost = (FragmentTabHost) mView.findViewById(android.R.id.tabhost);
			mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
			View scheduledTabStyle = LayoutInflater.from(getActivity()).inflate(R.layout.schedule_tab_style_layout, null);
			View historyTabStyle = LayoutInflater.from(getActivity()).inflate(R.layout.history_tab_style_layout, null);

			mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(scheduledTabStyle), FragmentInner_Tutions_Scheduled.class, null);
			mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(historyTabStyle), FragmentInner_Tutions_History.class, null);
		}else{
			((ViewGroup) mView.getParent()).removeView(mView);
		}

		return mView;
	}}