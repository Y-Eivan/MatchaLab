package com.example.matchalaab; 

public class MatchaItem {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final int imageResId;
    private final String tag;
    private final String type;

    public MatchaItem(int id, String name, String description, double price, int imageResId, String tag, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.tag = tag;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getTag() { return tag; }
    public String getType() { return type; }
}