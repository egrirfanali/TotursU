package com.strendent.tutorsu.FragmentMian;

import com.strendent.tutorsu.FragmentsInner.FragmentInner_BookMark;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_MyTrusted;
import com.strendent.tutorsu.FragmentsInner.FragmentInner_Recommended;
import com.strendent.tutorsu.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_MyFamily extends Fragment {


    public Fragment_MyFamily() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_my_family_row_item, container, false);
    }
}
