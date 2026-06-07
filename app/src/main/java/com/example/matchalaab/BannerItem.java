package com.example.matchalaab;

public class BannerItem {
    private final String title;      //main text on the banner card
    private final String subtitle;   //smaller text below title
    private final String tag;        //label badge shown at top left
    private final int imageResId;    //drawable resource for banner background image

    public BannerItem(String title, String subtitle, String tag, int imageResId) {
        this.title = title;
        this.subtitle = subtitle;
        this.tag = tag;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getTag() { return tag; }
    public int getImageResId() { return imageResId; }
}
