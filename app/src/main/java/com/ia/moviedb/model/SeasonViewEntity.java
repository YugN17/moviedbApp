package com.ia.moviedb.model;


import java.util.Map;

public class SeasonViewEntity {

    private Map<Integer, String> episodes; //episode number, episode name


    public Map<Integer, String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Map<Integer, String> episodes) {
        this.episodes = episodes;
    }
}
