package com.ia.entities.series;

import java.util.List;

public class SeriesSeason {
    final private int id;
    final private int seasonNumber;
    final private int episodeCount;
    private List<SeriesEpisode> episodes;

    public SeriesSeason(int id, int seasonNumber, int episodeCount, List<SeriesEpisode> episodes) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
        this.episodes = episodes;
    }

    public SeriesSeason(int id, int seasonNumber, int episodeCount) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    public int getId() {
        return id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public List<SeriesEpisode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<SeriesEpisode> episodes) {
        this.episodes = episodes;
    }
}
