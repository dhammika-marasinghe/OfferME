package com.illusionbox.www.offerme;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Janitha on 8/23/2015.
 */
public class Restaurant {
    public static final int FINE_DINING = 0;
    public static final int PIZZA = 1;
    public static final int PUB = 2;

    private String name;
    private String description;
    private String longDescription = "Food is any substance[1] consumed to provide nutritional support for the body. It is usually of plant or animal origin, and contains essential nutrients, such as fats, proteins, vitamins, or minerals. The substance is ingested by an organism and assimilated by the organism's cells to provide energy, maintain life, or stimulate growth.\n" +
            "\n" +
            "Historically, people secured food through two methods: hunting and gathering, and agriculture. Today, most of the food energy required by the ever increasing population of the world is supplied by the food industry.\n" +
            "\n" +
            "Food safety and food security are monitored by agencies like the International Association for Food Protection, World Resources Institute, World Food Programme, Food and Agriculture Organization, and International Food Information Council. They address issues such as sustainability, biological diversity, climate change, nutritional economics, population growth, water supply, and access to food.\n" +
            "\n" +
            "The right to food is a human right derived from the International Covenant on Economic, Social and Cultural Rights (ICESCR), recognizing the \"right to an adequate standard of living, including adequate food,\" as well as the \"fundamental right to be free from hunger.\"";
    private LatLng posititon;
    private int type;
    private int imgResource;
    private String offers;

    public String getOffers(){
        return offers;
    }

    public void setOffers(String offers){
        this.offers = offers;
    }

    public Restaurant(String name, String description, double latitude, double longitude, int type, String offers){
        this.name = name;
        this.description = description;
        this.posititon = new LatLng(latitude, longitude);
        this.type = type;
        this.offers = offers;
    }

    public void set_imgResource(int imgResource){
        this.imgResource = imgResource;
    }

    public int get_imgResource(){
        return this.imgResource;
    }

    public void set_longDescription(String longDescription){
        this.longDescription = longDescription;
    }

    public String get_longDescription(){
        return this.longDescription;
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

    @Override
    public String toString(){
        return name;
    }
}
