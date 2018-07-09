package com.ia.data.repository.datasource.cloud.model;

public class GenreDTO {

    private int id;
    private String name;

    public GenreDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public String getName() {
        return name;
    }
}
