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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Janitha on 8/24/2015.
 */
public class MyOfferDialog extends DialogFragment implements View.OnClickListener {

    Button done;

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonCompleted){
            dismiss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Offer Code");
        View view = inflater.inflate(R.layout.offer_code, null);
        done = (Button) view.findViewById(R.id.buttonCompleted);
        done.setOnClickListener(this);
        new DownloadImageTask((ImageView) view.findViewById(R.id.imageViewQR))
                .execute("http://dailygenius.com/wp-content/uploads/2014/06/qrcode.jpg");
        new RequestTask(view).execute("https://raw.githubusercontent.com/janithajc/share/master/OfferCode.code");
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
            try {
                bmImage.setImageBitmap(result);
            }catch (Exception e) {
                Toast.makeText(getActivity(), "No internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class RequestTask extends AsyncTask<String, String, String> {

        private String responseString = null;
        private TextView t;

        public RequestTask(View v){
            t = (TextView) v.findViewById(R.id.textViewOC);
        }

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
        try{
            super.onPostExecute(result);
            t.setText(responseString);
        }catch (Exception e) {
            Toast.makeText(getActivity(), "No internet connection.", Toast.LENGTH_SHORT).show();
        }
        }
    }
}
