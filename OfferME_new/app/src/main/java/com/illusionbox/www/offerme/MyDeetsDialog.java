package com.illusionbox.www.offerme;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.illusionbox.offerme.model.Restaurant;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Janitha on 8/24/2015.
 */
public class MyDeetsDialog extends DialogFragment implements View.OnClickListener {
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonCompleted){
            dismiss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("About Us");
        View view = inflater.inflate(R.layout.details_layout, null);

        TextView title, tp, address, brief;

        title = (TextView) view.findViewById(R.id.textViewDeetsTitle);
        title.setText(restaurant.getName());
        tp = (TextView) view.findViewById(R.id.textViewDeetsNumber);
        tp.setText(restaurant.getTel());
        address = (TextView) view.findViewById(R.id.textViewDeetsAddress);
        address.setText(restaurant.getAddress());
        brief = (TextView) view.findViewById(R.id.textViewDeetsBrief);
        brief.setText(restaurant.getDescription());

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

        ratingBar.setRating(restaurant.getRating());

        new DownloadImageTask((ImageView) view.findViewById(R.id.imageViewDeetsLogo)).execute(restaurant.getLogoUrl());
        return view;
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
            bmImage.setImageBitmap(result);
        }
    }
}
