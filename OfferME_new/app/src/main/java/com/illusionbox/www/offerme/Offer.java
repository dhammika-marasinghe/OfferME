package com.illusionbox.www.offerme;

/**
 * Created by Janitha on 8/23/2015.
 */
public class Offer {
    private String link;
    private int image = R.drawable.iblauncher;
    private boolean valid;
    private String name;
    private String description;

    public Offer(String name, String description, String link, boolean valid, int image) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.valid = valid;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }
}
