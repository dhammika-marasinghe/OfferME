package com.illusionbox.www.offerme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Janitha on 8/23/2015.
 */
public class OfferAdapter extends ArrayAdapter<Offer> {

    public OfferAdapter(Context context, List<Offer> objects) {
        super(context, R.layout.column_layout, objects);
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.column_layout,parent,false);

        Offer offer = getItem(position);

        TextView theTextViewT = (TextView)theView.findViewById(R.id.textViewName);

        theTextViewT.setText(offer.getName());

        TextView theTextViewD = (TextView)theView.findViewById(R.id.textViewOfferDesc);

        theTextViewD.setText(offer.getDescription());

        ImageView theImageView = (ImageView)theView.findViewById(R.id.imageViewOffer);

        theImageView.setImageResource(offer.getImage());

        return theView;
    }
}
