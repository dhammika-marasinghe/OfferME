package com.illusionbox.offerme.model;
// Generated 21-Sep-2015 19:17:54 by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Offer generated by hbm2java
 */
public class Offer  implements java.io.Serializable {


     private Integer idoffer;
     private Restaurant restaurant;
     private String title;
     private String description;
     private Date startDate;
     private Date endDate;
     private String repeat;
     private Boolean valid;
     private String imageUrl;
     private String type;
     private String offerCode;

    public Offer() {
    }

	
    public Offer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public Offer(Restaurant restaurant, String title, String description, Date startDate, Date endDate, String repeat, Boolean valid, String imageUrl, String type, String offerCode) {
       this.restaurant = restaurant;
       this.title = title;
       this.description = description;
       this.startDate = startDate;
       this.endDate = endDate;
       this.repeat = repeat;
       this.valid = valid;
       this.imageUrl = imageUrl;
       this.type = type;
       this.offerCode = offerCode;
    }

    public static Offer createOffer(String csvLine){
        String [] paras = csvLine.split(",");
        Offer off = new Offer(null, paras[0], paras[1], null, null, paras[4], Boolean.parseBoolean(paras[5]), paras[6], paras[7], paras[8]);
        return off;
    }
   
    public Integer getIdoffer() {
        return this.idoffer;
    }
    
    public void setIdoffer(Integer idoffer) {
        this.idoffer = idoffer;
    }
    public Restaurant getRestaurant() {
        return this.restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
    public Boolean getValid() {
        return this.valid;
    }
    
    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getOfferCode() {
        return this.offerCode;
    }
    
    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }




}

