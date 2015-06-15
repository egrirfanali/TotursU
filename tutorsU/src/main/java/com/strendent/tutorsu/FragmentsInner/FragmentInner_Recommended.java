package com.strendent.tutorsu.FragmentsInner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strendent.tutorsu.R;

public class FragmentInner_Recommended extends Fragment {

	private View mView;

	public FragmentInner_Recommended() {
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_demo_three, container, false);
			TextView textview=(TextView) mView.findViewById(R.id.name);
			textview.setText("Recommended");

		} else {
			mView=null;
		}

		return mView;
	}

}
