package com.ia.data.repository.datasource.cloud.model.series;

public class SeriesEpisodeDTO {
    private int id;
    private int episode_number;
    private String name;

    SeriesEpisodeDTO(int id, int episode_number, String name) {
        this.id = id;
        this.episode_number = episode_number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getEpisodeNumber() {
        return episode_number;
    }

    public String getName() {
        return name;
    }
}
