package com.ia.data.repository.datasource.cloud.model.series;

import java.util.List;

public class SeriesSeasonDTO {
    private int id;
    private int season_number;
    private int episode_count;
    private List<SeriesEpisodeDTO> episodes;

    public int getId() {
        return id;
    }

    public int getSeasonNumber() {
        return season_number;
    }

    public int getEpisodeCount() {
        return episode_count;
    }

    public List<SeriesEpisodeDTO> getEpisodes() {
        return episodes;
    }
}
