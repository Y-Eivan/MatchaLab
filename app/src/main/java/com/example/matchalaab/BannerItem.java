package com.example.matchalaab;

public class BannerItem {
    private final String title;
    private final String subtitle;
    private final String tag;
    private final int backgroundColor;

    public BannerItem(String title, String subtitle, String tag, int backgroundColor) {
        this.title = title;
        this.subtitle = subtitle;
        this.tag = tag;
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getTag() { return tag; }
    public int getBackgroundColor() { return backgroundColor; }
}
