package com.ia.entities;

public class Actor {

    private int id;
    private String character;
    private String name;
    private String image;

    public Actor(int id, String character, String name, String image) {
        this.id = id;
        this.character = character;
        this.name = name;
        this.image = image;
    }

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
