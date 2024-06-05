package com.example.recyclevapp;

public class PokemonModel {
    private String name;
    private String type;
    private int imageResId;
    private int id_;

    public PokemonModel(String name, String type, int imageResId, int id_) {
        this.name = name;
        this.type = type;
        this.imageResId = imageResId;
        this.id_ = id_;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getId_() {
        return id_;
    }
}
