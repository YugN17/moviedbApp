package com.ia.entities.series;

public class SeriesEpisode {
    private int id;
    private int episodeNumber;
    private String name;

    public SeriesEpisode(int id, int episodeNumber, String name) {
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }
}
