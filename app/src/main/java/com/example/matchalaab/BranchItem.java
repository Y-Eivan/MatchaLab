package com.example.matchalaab;

public class BranchItem {
    private String name;
    private String address;

    public BranchItem(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
}