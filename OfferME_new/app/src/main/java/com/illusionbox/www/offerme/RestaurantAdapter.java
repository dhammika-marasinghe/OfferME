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
public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    public RestaurantAdapter(Context context, List<Restaurant> objects) {
        super(context, R.layout.restaurant_layout, objects);
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.restaurant_layout,parent,false);

        Restaurant restaurant = getItem(position);

        TextView theTextViewT = (TextView)theView.findViewById(R.id.textViewTitle);

        theTextViewT.setText(restaurant.getName());

        TextView theTextViewD = (TextView)theView.findViewById(R.id.textViewDesc);

        theTextViewD.setText(restaurant.getDescription());

        ImageView theImageView = (ImageView)theView.findViewById(R.id.imageViewRes);

        if(restaurant.getType() == Restaurant.FINE_DINING) {
            theImageView.setImageResource(R.drawable.finedining);
            restaurant.set_imgResource(R.drawable.finedining);
        }else if(restaurant.getType() == Restaurant.PIZZA){
            theImageView.setImageResource(R.drawable.pizza);
            restaurant.set_imgResource(R.drawable.pizza);
        }else{
            theImageView.setImageResource(R.drawable.pub);
            restaurant.set_imgResource(R.drawable.pub);
        }

        return theView;
    }
}
