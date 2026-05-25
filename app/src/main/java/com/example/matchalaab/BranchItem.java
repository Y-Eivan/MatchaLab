package com.example.matchalaab;

public class BranchItem {
    private final String name;
    private final String address;
    private final String openingHours;
    private final String tag;
    private final double lat;
    private final double lng;

    public BranchItem(String name, String address, String openingHours,
                      String tag, double lat, double lng) {
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
        this.tag = tag;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName()         { return name; }
    public String getAddress()      { return address; }
    public String getOpeningHours() { return openingHours; }
    public String getTag()          { return tag; }
    public double getLat()          { return lat; }
    public double getLng()          { return lng; }
}
