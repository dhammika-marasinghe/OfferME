package com.illusionbox.www.offerme;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Janitha on 8/25/2015.
 */
public class LocationHandler {

    private LatLng UserLocation;

    private LatLng restaurantLocation;

    private float minDist = 500;

    public LatLng getUserLocation() {
        return UserLocation;
    }

    public void setUserLocation(LatLng userLocation) {
        UserLocation = userLocation;
    }

    public LatLng getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(LatLng restaurantLocation) {
        restaurantLocation = restaurantLocation;
    }

    public float getMinDist() {
        return minDist;
    }

    public void setMinDist(float minDist) {
        this.minDist = minDist;
    }

    public LocationHandler(LatLng UserLatLng) {
        UserLocation = UserLatLng;
    }

    public LocationHandler(LatLng UserLatLng, LatLng ResLatLng) {
        UserLocation = UserLatLng;
        restaurantLocation = ResLatLng;
    }

    public boolean checkinAvailable(LatLng RestaurantLocation){
        float [] results = new float[1];
        Location.distanceBetween(UserLocation.latitude, UserLocation.longitude, RestaurantLocation.latitude, RestaurantLocation.longitude, results);
        if(results[0] <= minDist){
            return true;
        }
        return false;
    }

    public boolean checkinAvailable(Restaurant restaurant){
        float [] results = new float[1];
        Location.distanceBetween(UserLocation.latitude, UserLocation.longitude, restaurant.getPosititon().latitude, restaurant.getPosititon().longitude, results);
        if(results[0] <= minDist){
            return true;
        }
        return false;
    }

    public boolean checkinAvailable(){
        float [] results = new float[1];
        Location.distanceBetween(UserLocation.latitude, UserLocation.longitude, restaurantLocation.latitude, restaurantLocation.longitude, results);
        if(results[0] <= minDist){
            return true;
        }
        return false;
    }
}
