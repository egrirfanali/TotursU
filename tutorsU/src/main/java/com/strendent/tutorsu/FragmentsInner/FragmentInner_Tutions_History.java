package com.strendent.tutorsu.FragmentsInner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.strendent.tutorsu.Adapters.Adapter_Schedule_Tutions;
import com.strendent.tutorsu.Models.Fav_Location_model;
import com.strendent.tutorsu.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentInner_Tutions_History extends Fragment {

	private View mView;
	ListView scheduledTutionsList;
	List<Fav_Location_model> scheduledTutorsDataList = new ArrayList<Fav_Location_model>();

	public FragmentInner_Tutions_History() {
		setRetainInstance(true);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_inner_tutions_scheduled_main_layout, container, false);


		initList();
		initView();


		Adapter_Schedule_Tutions scheduleAdapter = new Adapter_Schedule_Tutions(scheduledTutorsDataList, getActivity());
		scheduledTutionsList.setAdapter(scheduleAdapter);
		scheduledTutionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), position + " no Clicked ", Toast.LENGTH_SHORT).show();
			/*	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which){
							case DialogInterface.BUTTON_POSITIVE:
								//Yes button clicked
								break;

							case DialogInterface.BUTTON_NEGATIVE:
								//No button clicked
								break;
						}
					}
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
						.setNegativeButton("No", dialogClickListener).show();*/
			}
		});
		return mView;
	}

	private void initList() {
		// We populate the planets

		scheduledTutorsDataList.add(new Fav_Location_model("Home", "Abcdef", "Abcdefgh"));
		scheduledTutorsDataList.add(new Fav_Location_model("Work", "12345", "345646465465564"));
		scheduledTutorsDataList.add(new Fav_Location_model("Office", "oooooo", "aaaaaaa"));
		scheduledTutorsDataList.add(new Fav_Location_model("Sports", "zzzzzzzzz", "yyyyyyyyyyyy"));
	}

	private void initView() {

		if (mView != null) {
			scheduledTutionsList = (ListView) mView.findViewById(R.id.listView_scheduled_tutions);
		}

	}
}
