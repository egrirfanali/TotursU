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
 * Created by user on 6/18/2015.
 */
public class Adapter_Promotions_History  extends ArrayAdapter<Fav_Location_model> {

    private List<Fav_Location_model> paymentHistoryList;
    private Context context;


    public Adapter_Promotions_History(List<Fav_Location_model> paymentHistoryList, Context ctx) {
        super(ctx, R.layout.fragment_inner_promotions_history_row_item, paymentHistoryList);
        this.paymentHistoryList = paymentHistoryList;
        this.context = ctx;
    }

    public int getCount() {
        return paymentHistoryList.size();
    }

    public long getItemId(int position) {
        return paymentHistoryList.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        PlanetHolder holder = new PlanetHolder();

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.fragment_inner_promotions_history_row_item, null);

            TextView tvDate = (TextView) v.findViewById(R.id.tv_date);
            TextView tvPromotionCode = (TextView) v.findViewById(R.id.tv_promotion_code);
            TextView tvPromotionValue = (TextView) v.findViewById(R.id.tv_value);
            TextView tvPromotionExpiryDate = (TextView) v.findViewById(R.id.tv_expire_date);

//            tv_expire_date

            holder.tvDate = tvDate;
            holder.tvPromotionCode = tvPromotionCode;
            holder.tvPromotionValue = tvPromotionValue;
            holder.tvPromotionExpiryDate = tvPromotionExpiryDate;

            v.setTag(holder);
        } else
            holder = (PlanetHolder) v.getTag();

        Fav_Location_model favLocationModel = paymentHistoryList.get(position);
        holder.tvDate.setText(favLocationModel.getTitle());
        holder.tvPromotionCode.setText("" + favLocationModel.getPrimaryAddress());
        holder.tvPromotionValue.setText("" + favLocationModel.getSecondryAddress());
        holder.tvPromotionValue.setText("" + favLocationModel.getSecondryAddress());


        return v;
    }

	/* *********************************
     * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/

    private static class PlanetHolder {
        public TextView tvDate;
        public TextView tvPromotionCode;
        public TextView tvPromotionValue;
        public TextView tvPromotionExpiryDate;

    }


}



