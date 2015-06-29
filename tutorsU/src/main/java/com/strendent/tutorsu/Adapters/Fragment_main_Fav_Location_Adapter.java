package com.strendent.tutorsu.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.strendent.tutorsu.Models.Fav_Location_model;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.CircularImageView;

import java.util.List;

/**
 * Created by user on 6/16/2015.
 */
public class Fragment_main_Fav_Location_Adapter extends ArrayAdapter<Fav_Location_model> {

    private List<Fav_Location_model> locationList;
    private Context context;
    CircularImageView imageViewMapImage;

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
             imageViewMapImage=(CircularImageView) v.findViewById(R.id.image_view_map_image);


            holder.tvLocationTitle = tvLocationTitle;
            holder.tvlocationAddressPrimary = tvlocationAddressPrimary;
            holder.tvSecondryAddress = tvSecondryAddress;
            holder.imageViewMapImage=imageViewMapImage;
            v.setTag(holder);


        } else
            holder = (PlanetHolder) v.getTag();

        Fav_Location_model favLocationModel = locationList.get(position);
       final String fullAddress = favLocationModel.getPrimaryAddress()+","+favLocationModel.getSecondryAddress();
        holder.tvLocationTitle.setText(favLocationModel.getTitle());

        holder.tvlocationAddressPrimary.setText("" + favLocationModel.getPrimaryAddress());
        holder.tvSecondryAddress.setText("" + favLocationModel.getSecondryAddress());
        holder.imageViewMapImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("ImageClicked","ImagezVIewIn listCLicked");
                Uri uri = Uri.parse("https://www.google.com/maps/place/" + fullAddress);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                context.startActivity(intent);
            }

        });

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
        public CircularImageView imageViewMapImage;
    }


}


