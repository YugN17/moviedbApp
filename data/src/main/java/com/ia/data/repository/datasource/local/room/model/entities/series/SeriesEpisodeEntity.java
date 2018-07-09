package com.ia.data.repository.datasource.local.room.model.entities.series;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = SeriesSeasonEntity.class, parentColumns = {"seriesId", "seasonNumber"}, childColumns = {"seriesId", "seasonNumber"}),
        primaryKeys = {"seriesId", "seasonNumber", "episodeNumber"})
public class SeriesEpisodeEntity {

    private int id;
    private int episodeNumber;
    private int seasonNumber;
    private int seriesId;
    private String name;

    public SeriesEpisodeEntity(int id, int episodeNumber, int seasonNumber, int seriesId, String name) {
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.seriesId = seriesId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
