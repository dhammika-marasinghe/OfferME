package com.illusionbox.www.offerme;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Janitha on 8/23/2015.
 */
public class Restaurant {
    public static final int FINE_DINING = 0;
    public static final int PIZZA = 1;
    public static final int PUB = 2;
    public static final int FAST_FOOD = 2;

    private String name;
    private String description;
    private LatLng posititon;
    private int type;
    private int imgResource;
    private String offers;

    private String details;

    public String getOffers(){
        return offers;
    }

    public void setOffers(String offers){
        this.offers = offers;
    }

    public Restaurant(String name, String description, double latitude, double longitude, int type, String offers, String details){
        this.name = name;
        this.description = description;
        this.posititon = new LatLng(latitude, longitude);
        this.type = type;
        this.offers = offers;
        this.details = details;
    }

    public void set_imgResource(int imgResource){
        this.imgResource = imgResource;
    }

    public int get_imgResource(){
        return this.imgResource;
    }

    public void set_longDescription(String longDescription){
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getType(){
        return type;
    }

    public LatLng getPosititon(){
        return posititon;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString(){
        return name;
    }
}
