package com.strendent.tutorsu.FragmentsInner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.strendent.tutorsu.Adapters.Adapter_Promotions_History;
import com.strendent.tutorsu.Adapters.Adapter_Schedule_Tutions;
import com.strendent.tutorsu.Models.Fav_Location_model;
import com.strendent.tutorsu.R;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
/**
 * Created by user on 6/18/2015.
 */
public class FragmentInner_Promotion_History extends Fragment {

    private View mView;
    ListView paymentsHistoryList;
    List<Fav_Location_model> paymentHistoryDataList = new ArrayList<Fav_Location_model>();

    public FragmentInner_Promotion_History() {
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment__inner_promotions_history_main, container, false);


        initList();
        initView();


        Adapter_Promotions_History scheduleAdapter = new Adapter_Promotions_History(paymentHistoryDataList, getActivity());
        paymentsHistoryList.setAdapter(scheduleAdapter);
        paymentsHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        paymentHistoryDataList.add(new Fav_Location_model("28 May", "123456", "28 june"));
        paymentHistoryDataList.add(new Fav_Location_model("29 May", "***12345", "29 june"));
        paymentHistoryDataList.add(new Fav_Location_model("11 May", "5456465", "25 dec"));
    }

    private void initView() {

        if (mView != null) {
            paymentsHistoryList = (ListView) mView.findViewById(R.id.listView_payments_history);
        }

    }
}

