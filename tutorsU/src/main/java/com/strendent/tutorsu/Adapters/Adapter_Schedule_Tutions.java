package com.strendent.tutorsu.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.strendent.tutorsu.Models.Fav_Location_model;
import com.strendent.tutorsu.R;

import java.util.List;

/**
 * Created by user on 6/17/2015.
 */
public class Adapter_Schedule_Tutions extends ArrayAdapter<Fav_Location_model> {

    private List<Fav_Location_model> locationList;
    private Context context;


    public Adapter_Schedule_Tutions(List<Fav_Location_model> locationListFromFragment, Context ctx) {
        super(ctx, R.layout.fragment_main_fav_location_row_item, locationListFromFragment);
        this.locationList = locationListFromFragment;
        this.context = ctx;
    }

    public int getCount() {
        return locationList.size();
    }

    public long getItemId(int position) {
        return locationList.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        PlanetHolder holder = new PlanetHolder();

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.fragment_inner_tutions_scheduled_row_item, null);

            TextView tvAddress = (TextView) v.findViewById(R.id.tv_address);
            TextView tv_tutor_id_text = (TextView) v.findViewById(R.id.tv_value);
            TextView tv_tutor_id_value = (TextView) v.findViewById(R.id.tv_expire_date);


            holder.tvAddress = tvAddress;
            holder.tv_tutor_id_text = tv_tutor_id_text;
            holder.tv_tutor_id_value = tv_tutor_id_value;
            v.setTag(holder);
        } else
            holder = (PlanetHolder) v.getTag();

        Fav_Location_model favLocationModel = locationList.get(position);
        holder.tvAddress.setText(favLocationModel.getTitle());
        holder.tv_tutor_id_text.setText("" + favLocationModel.getPrimaryAddress());
        holder.tv_tutor_id_value.setText("" + favLocationModel.getSecondryAddress());


        return v;
    }

	/* *********************************
     * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/

    private static class PlanetHolder {
        public TextView tvAddress;
        public TextView tv_tutor_id_text;
        public TextView tv_tutor_id_value;
    }


}


