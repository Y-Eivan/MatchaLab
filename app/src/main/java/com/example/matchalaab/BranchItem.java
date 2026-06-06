package com.example.matchalaab;

public class BranchItem {
    private final String name;         //store display name
    private final String address;      //full street address shown on card
    private final String openingHours; //e.g. "Mon-Sun 07:00 - 22:00"

    public BranchItem(String name, String address, String openingHours) {
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
    }

    public String getName()         { return name; }
    public String getAddress()      { return address; }
    public String getOpeningHours() { return openingHours; }
}
