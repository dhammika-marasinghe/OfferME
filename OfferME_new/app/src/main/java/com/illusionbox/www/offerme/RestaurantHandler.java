package com.illusionbox.www.offerme;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janitha on 9/21/2015.
 */
public class RestaurantHandler {
    static ArrayList<Restaurant> restaurants;
    static RestaurantAdapter theAdapter;

    public static void createRestaurantList(String url, ArrayList<Restaurant> restaurants, RestaurantAdapter theAdapter){
        theAdapter = theAdapter;
        restaurants = restaurants;
        new RequestTask().doInBackground(url);
    }

    static class RequestTask extends AsyncTask<String, String, String> {

        private String responseString = null;

        @Override
        protected String doInBackground(String... uri) {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
            HttpConnectionParams.setSoTimeout(httpParameters, 30000);

            HttpClient httpclient = new DefaultHttpClient(httpParameters);
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
            super.onPostExecute(result);
            try{
                ParseString(responseString);
            }catch (Exception e) {
            }
        }

        void ParseString(String listOfRes){
            String [] resList = listOfRes.split("~END~");
            for(int i=1; i < resList.length; i++){
                String csvLine = resList[i];
                restaurants.add(Restaurant.createRestaurant(csvLine));
            }
            theAdapter.notifyDataSetChanged();
        }
    }

}
