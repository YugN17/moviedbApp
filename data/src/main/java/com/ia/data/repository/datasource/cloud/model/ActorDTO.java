package com.ia.data.repository.datasource.cloud.model;


import com.google.gson.annotations.SerializedName;

public class ActorDTO {

    private int id;
    private String character;
    private String name;
    @SerializedName("profile_path")
    private String image;

    public int getId() {
        return id;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
