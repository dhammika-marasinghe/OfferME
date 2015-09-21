package com.illusionbox.www.offerme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.illusionbox.offerme.model.Restaurant;

import java.io.InputStream;
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

        theImageView.setImageResource(R.drawable.finedining);

        new DownloadImageTask(theImageView)
                .execute(restaurant.getBannerUrl());

        return theView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            //super.onPostExecute(result);
            try {
                bmImage.setImageBitmap(result);
            }catch (Exception e) {
                Toast.makeText(getContext(), "No internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
