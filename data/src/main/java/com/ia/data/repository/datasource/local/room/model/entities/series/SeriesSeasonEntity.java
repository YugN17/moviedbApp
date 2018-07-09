package com.ia.data.repository.datasource.local.room.model.entities.series;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = SeriesEntity.class, parentColumns = "id", childColumns = "seriesId"),
        primaryKeys = {"seriesId", "seasonNumber"})
public class SeriesSeasonEntity {

    private int id;
    private int seriesId;
    private int seasonNumber;
    private int episodeCount;

    public SeriesSeasonEntity(int id, int seriesId, int seasonNumber, int episodeCount) {
        this.id = id;
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

}
