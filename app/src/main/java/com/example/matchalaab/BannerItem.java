package com.example.matchalaab;

public class BannerItem {
    private final String title;
    private final String subtitle;
    private final String tag;
    private final int imageResId;

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
