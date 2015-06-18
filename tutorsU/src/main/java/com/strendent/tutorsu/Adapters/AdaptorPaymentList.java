package com.strendent.tutorsu.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.strendent.tutorsu.Models.PaymentItem;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Utilities.Utility;

import java.util.List;

public class AdaptorPaymentList extends ArrayAdapter<PaymentItem>{

    public AdaptorPaymentList(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdaptorPaymentList(Context context, int resource, List<PaymentItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.payment_card_list_items, null);
        }

        PaymentItem paymentItem = getItem(position);

        if (paymentItem != null) {
        	
            ImageView imageViewCardType = (ImageView) v.findViewById(R.id.imageViewCardType);
            TextView tvCardNumber = (TextView) v.findViewById(R.id.tvCardNumber);
            TextView tvCardPriority = (TextView) v.findViewById(R.id.tvCardPriority);
            ImageView imageViewArrowProceeder = (ImageView) v.findViewById(R.id.imageViewArrowProceeder);

            if (imageViewCardType != null) {
                Bitmap bitmap= Utility.convertByteArrayToBitmap(paymentItem.getByteArray());
                Drawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
                imageViewCardType.setImageDrawable(drawable);
            }
            if (tvCardNumber != null) {
                tvCardNumber.setText(getContext().getResources().getString(R.string.dotted_card_numbr)+paymentItem.getCardNumber().toString().substring(14));
            }
            if (tvCardPriority != null && paymentItem.isPrimaryCard()) {

                tvCardPriority.setText(getContext().getResources().getString(R.string.primary_card));
            }
            if (imageViewArrowProceeder != null) {
                imageViewArrowProceeder.setImageResource(R.drawable.ic_menu_arrow);
            }
        }

        return v;
    }

}