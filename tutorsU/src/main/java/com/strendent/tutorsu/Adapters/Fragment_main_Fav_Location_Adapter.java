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
 * Created by user on 6/16/2015.
 */
public class Fragment_main_Fav_Location_Adapter extends ArrayAdapter<Fav_Location_model> {

    private List<Fav_Location_model> locationList;
    private Context context;


    public Fragment_main_Fav_Location_Adapter(List<Fav_Location_model> locationListFromFragment, Context ctx) {
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
            v = inflater.inflate(R.layout.fragment_main_fav_location_row_item, null);

            TextView tvLocationTitle = (TextView) v.findViewById(R.id.tv_address_title);
            TextView tvlocationAddressPrimary = (TextView) v.findViewById(R.id.tv_address_primary);
            TextView tvSecondryAddress = (TextView) v.findViewById(R.id.tv_secondry_address);


            holder.tvLocationTitle = tvLocationTitle;
            holder.tvlocationAddressPrimary = tvlocationAddressPrimary;
            holder.tvSecondryAddress = tvSecondryAddress;
            v.setTag(holder);
        } else
            holder = (PlanetHolder) v.getTag();

        Fav_Location_model favLocationModel = locationList.get(position);
        holder.tvLocationTitle.setText(favLocationModel.getTitle());
        holder.tvlocationAddressPrimary.setText("" + favLocationModel.getPrimaryAddress());
        holder.tvSecondryAddress.setText("" + favLocationModel.getSecondryAddress());


        return v;
    }

	/* *********************************
     * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/

    private static class PlanetHolder {
        public TextView tvLocationTitle;
        public TextView tvlocationAddressPrimary;
        public TextView tvSecondryAddress;
    }


}


