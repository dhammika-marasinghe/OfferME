package com.illusionbox.www.offerme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;
import com.illusionbox.offerme.model.Offer;

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

        theTextViewT.setText(offer.getTitle());

        TextView theTextViewD = (TextView)theView.findViewById(R.id.textViewOfferDesc);

        theTextViewD.setText(offer.getDescription());

        ImageView theImageView = (ImageView)theView.findViewById(R.id.imageViewOffer);

        theImageView.setImageResource(R.drawable.iblauncher);

        new DownloadImageTask(theImageView)
                .execute(offer.getImageUrl());

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
