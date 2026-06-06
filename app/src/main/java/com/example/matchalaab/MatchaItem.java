package com.example.matchalaab;

public class MatchaItem {
    private final int id;               //unique item id
    private final String name;          //display name on card and detail screen
    private final String description;   //short subtitle shown on product card
    private final String detailDescription; //full body text shown on detail screen
    private final double price;         //price in IDR 
    private final int imageResId;       //drawable resource id for product image
    private final String tag;           //category used by chip filter
    private final String type;          //product type

    public MatchaItem(int id, String name, String description, String detailDescription,
                      double price, int imageResId, String tag, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.detailDescription = detailDescription;
        this.price = price;
        this.imageResId = imageResId;
        this.tag = tag;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDetailDescription() { return detailDescription; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getTag() { return tag; }
    public String getType() { return type; }
}
