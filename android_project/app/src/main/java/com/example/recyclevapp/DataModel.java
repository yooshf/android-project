package com.example.recyclevapp;

public class DataModel {
    private String name;
    private String description;
    private int imageResource;

    public DataModel(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}
