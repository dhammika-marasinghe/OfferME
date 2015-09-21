package com.illusionbox.www.offerme;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class MapsActivity extends FragmentActivity {

    private ProgressDialog progressDialog;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    ArrayList<String> nearby;
    ArrayList<Restaurant> nearbyRestaurants;
    HashMap<String,MarkerOptions> nearbyMarks;
    RestaurantAdapter theAdapter;
    ListView theListView;
    int radius = 2000;
    int initRadius = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Locating your device...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        checkLocationStatus();

        Intent fromActivity = getIntent();

        try {
            radius = fromActivity.getExtras().getInt("radius");
        }catch (Exception e){
            radius = 2000;
        }

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        /*String[] nearbyRestaurants = {"KaMuKo", "MC Donalds", "KFC", "Dinemore",
                "Bay Leaf", "Oro", "Pizza Hut", "Domino's"};*/

        nearby = new ArrayList<>();
        nearbyRestaurants = new ArrayList<>();
        nearbyMarks = new HashMap<>();
        nearby.add("Getting Location...");

        theAdapter = new RestaurantAdapter(this, nearbyRestaurants);

        theListView = (ListView) findViewById(R.id.listView);
        theListView.setAdapter(theAdapter);
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String restaurantSelected = "Pinpointing your location!";
                Toast.makeText(MapsActivity.this, restaurantSelected, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void checkLocationStatus(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Location Service Not Enabled!");
            dialog.setPositiveButton("Open location settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
            dialog.show();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    Location lastKnown = new Location("");

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        Location dummyLocation = new Location("");
        Location dummyLocation1 = new Location("");
        /*UOP
        7.254642, 80.591309
        */
        dummyLocation.setLatitude(7.279107);
        dummyLocation.setLongitude(80.698602);
        /*dummyLocation.setLatitude(7.254642);
        dummyLocation.setLongitude(80.591309);*/

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dummyLocation.getLatitude(), dummyLocation.getLongitude()), 10));

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(final Location location) {
                float []  dists = new float[1];
                Location.distanceBetween(lastKnown.getLatitude(),lastKnown.getLongitude(),location.getLatitude(),location.getLongitude(),dists);
                if(dists[0] > 10 || radius != initRadius) {
                    lastKnown = location;
                    initRadius = radius;
                    new RequestTask(location).execute("http://192.168.1.2:8084/OfferMe_web/fetch_restaurant?latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude() + "&radius=" + radius);
                    //Toast.makeText(getBaseContext(), "http://192.168.1.11:8084/OfferMe_web/fetch_restaurant?latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude() + "&radius=" + radius, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void settingsClick(View view){
        Intent settingsIntent = new Intent(MapsActivity.this, SettingsActivity.class);
        settingsIntent.putExtra("radius", radius);
        startActivity(settingsIntent);
    }
    class RequestTask extends AsyncTask<String, String, String> {

        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        private String responseString = null;
        Location location = new Location("");

        public RequestTask(Location location){
            this.location = location;
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
                finish();
                e.printStackTrace();
                //TODO Handle problems..
            } catch (IOException e) {
                finish();
                e.printStackTrace();
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
            //try{
                ParseString(responseString);
            /*}catch (Exception e) {
                Toast.makeText(getBaseContext(),"Exception thrown!",Toast.LENGTH_LONG).show();
            }*/
        }

        void ParseString(String listOfRes){
            String [] resList = listOfRes.split("~END~");
            for(int i=1; i < resList.length; i++){
                String csvLine = resList[i];
                if(csvLine.split(",").length == 14) {
                    restaurants.add(Restaurant.createRestaurant(csvLine));
                }
            }
            MarkerOptions[] resMarker = new MarkerOptions[restaurants.size()];

            progressDialog.dismiss();
            mMap.clear();
            nearby.clear();
            nearbyRestaurants.clear();
            nearbyMarks.clear();

            int i = 0;
            for (Restaurant r : restaurants) {
                resMarker[i] = new MarkerOptions().position(r.getLocation()).title(r.getName());
                nearbyRestaurants.add(r);
                i++;
            }

            for (MarkerOptions a : resMarker) {
                float[] results = new float[1];
                Location.distanceBetween(a.getPosition().latitude, a.getPosition().longitude, location.getLatitude(), location.getLongitude(), results);
                String distance = "Distance: " + ((int) results[0] / 1000 > 0 ? (int) results[0] / 1000 + " km " : "") + (int) results[0] % 1000 + " m";
                    /*if(results[0] < radius)
                    {
                        a.snippet(distance);
                        //nearby.add(a.getTitle());
                        nearbyRestaurants.add(restaurants.(a.getTitle()));
                        nearbyMarks.put(a.getTitle(), a);
                        mMap.addMarker(a);
                    }
                    else
                    {*/
                a.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                a.snippet(distance);
                mMap.addMarker(a);
                //}
            }

            theAdapter.notifyDataSetChanged();
            theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Restaurant res = (Restaurant) parent.getItemAtPosition(position);
                    String resTitle = res.getName();
                    String restaurantSelected = "Locating " + resTitle;
                    Toast.makeText(MapsActivity.this, restaurantSelected, Toast.LENGTH_SHORT).show();
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(res.getLocation(), 15));
                }
            });
            theListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Restaurant res = (Restaurant) parent.getItemAtPosition(position);
                       /* MyDialogFragment d = new MyDialogFragment();
                        d.setValues(res.getName(), res.getDescription());
                        d.show(getFragmentManager(), "theDialog");*/
                    Intent secondScreenIntent = new Intent(MapsActivity.this, SecondScreen.class);
                    final int result = 1;
                    secondScreenIntent.putExtra("title", res.getName());
                    secondScreenIntent.putExtra("image", res.getBannerUrl());
                    secondScreenIntent.putExtra("offersLink", "http://192.168.1.2:8084/OfferMe_web/fetch_offer?id="+res.getIdrestaurant());
                    secondScreenIntent.putExtra("resLat", res.getLocation().latitude);
                    secondScreenIntent.putExtra("resLng", res.getLocation().longitude);
                    secondScreenIntent.putExtra("latitude", location.getLatitude());
                    secondScreenIntent.putExtra("longitude", location.getLongitude());
                    secondScreenIntent.putExtra("deetsUrl", res.getDescription());
                    secondScreenIntent.putExtra("restaurant", res.toCSVLine().replace("~END~",""));
                    startActivity(secondScreenIntent);
                    return true;
                }
            });
        }
    }
}
