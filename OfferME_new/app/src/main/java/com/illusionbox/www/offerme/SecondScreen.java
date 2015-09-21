package com.illusionbox.www.offerme;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

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
import com.illusionbox.offerme.model.Offer;
import com.illusionbox.offerme.model.Restaurant;

/**
 * Created by Janitha on 8/23/2015.
 */
public class SecondScreen extends Activity {

    ArrayList<Offer> offers = new ArrayList<>();

    float distance;
    private boolean checkedIn;
    Button checkin;
    String deetsUrl;
    LatLng myLatLng;
    LatLng resLatLng;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_layout);

        Intent fromActivity = getIntent();

        String title =  fromActivity.getExtras().getString("title");
        //String description =  fromActivity.getExtras().getString("description");
        String image = fromActivity.getExtras().getString("image");
        String offersLink = fromActivity.getExtras().getString("offersLink");
        double resLat = fromActivity.getExtras().getDouble("resLat");
        double resLng = fromActivity.getExtras().getDouble("resLng");
        double myLat = fromActivity.getExtras().getDouble("latitude");
        double myLng = fromActivity.getExtras().getDouble("longitude");
        deetsUrl = fromActivity.getExtras().getString("deetsUrl");
        restaurant = Restaurant.createRestaurant(fromActivity.getExtras().getString("restaurant"));

        myLatLng = new LatLng(myLat, myLng);
        resLatLng = new LatLng(resLat, resLng);


        checkin = (Button) findViewById(R.id.buttonCheckin);

        /*float []  results = new float[1];
        Location.distanceBetween(myLat, myLng, resLat, resLng, results);*/

        LocationHandler locationHandler = new LocationHandler(myLatLng, resLatLng);

        if(locationHandler.checkinAvailable()){
            checkin.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(SecondScreen.this, "You must be within 50 meters to check in", Toast.LENGTH_LONG).show();
        }

        new RequestTask().execute(offersLink);

        TextView titleView = (TextView) findViewById(R.id.textTitle);
        titleView.setText(title);

        /*TextView descView = (TextView) findViewById(R.id.textDescription);
        descView.setText(description);*/

        ImageView img = (ImageView) findViewById(R.id.imageViewDet);
        img.setImageResource(R.drawable.finedining);

        new DownloadImageTask(img)
                .execute(image);

    }

    public void onBackClick(View view) {
        finish();
    }

    public void onCheckinClick(View view) {
        if(!checkedIn) {

            checkedIn = true;
            Toast.makeText(SecondScreen.this, "Checkin Success!", Toast.LENGTH_SHORT).show();
            checkin.setText("Check out!");
        }else{
            checkedIn = false;
            Toast.makeText(SecondScreen.this, "Check Out Success!", Toast.LENGTH_SHORT).show();
            checkin.setText("Check in!");
        }
    }

    public void onClickDeets(View view) {
        MyDeetsDialog d = new MyDeetsDialog();
        d.setRestaurant(restaurant);
        d.show(getFragmentManager(), "theDeets");
    }

    class RequestTask extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog = new ProgressDialog(SecondScreen.this);
        private ArrayList<Offer> offers = new ArrayList<>();
        private String responseString = null;
        private OfferAdapter theOfferAdapter;
        private ListView theListView;

        protected void onPreExecute() {
            progressDialog.setMessage("Downloading your data...");
            progressDialog.show();
            theOfferAdapter = new OfferAdapter(SecondScreen.this, offers);
            theListView = (ListView) findViewById(R.id.listViewOffers);
            theListView.setAdapter(theOfferAdapter);
            /*progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    RequestTask.this.cancel(true);
                }
            });*/
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
                //Toast.makeText(SecondScreen.this, "No internet connection.", Toast.LENGTH_SHORT).show();
                finish();
                //TODO Handle problems..
            } catch (IOException e) {
                //Toast.makeText(SecondScreen.this, "No internet connection.", Toast.LENGTH_SHORT).show();
                finish();
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            //try {
                String[] csv = responseString.split("\n");
                for (int i=1; i<csv.length; i++) {
                    String s = csv[i];
                    String[] vals = s.split(",");
                    //Toast.makeText(SecondScreen.this, s, Toast.LENGTH_SHORT).show();
                    if (vals.length == 9) {
                        offers.add(Offer.createOffer(s));
                    }
                }
                theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                        if (!checkedIn) {
                            Toast.makeText(SecondScreen.this, "Please Check In", Toast.LENGTH_SHORT).show();
                        } else {
                            final AlertDialog.Builder dialog = new AlertDialog.Builder(SecondScreen.this);
                            dialog.setMessage("Take Offer?");
                            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    Intent myIntent = new Intent(SecondScreen.this, TakeOffer.class);
                                    Offer offer = (Offer) parent.getItemAtPosition(position);
                                    myIntent.putExtra("OfferCode", offer.getOfferCode());
                                    myIntent.putExtra("OfferQR", "http://dailygenius.com/wp-content/uploads/2014/06/qrcode.jpg");
                                    startActivity(myIntent);
                                    //get gps
                                }
                            });
                            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                }
                            });
                            dialog.show();
                        }
                    }
                });
                theOfferAdapter.notifyDataSetChanged();
            /*} catch (Exception e){
                e.printStackTrace();
                Toast.makeText(SecondScreen.this, "No internet connection.", Toast.LENGTH_SHORT).show();
            }*/
        }
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
            }
        }
    }

}
