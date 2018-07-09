package com.ia.data.repository.datasource.local.room.model.entities.series;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.ia.data.repository.datasource.local.room.model.entities.GenreEntity;

@Entity(foreignKeys = {@ForeignKey(entity = SeriesEntity.class, parentColumns = "id", childColumns = "seriesId"),
        @ForeignKey(entity = GenreEntity.class, parentColumns = "id", childColumns = "genreId")},
        indices = {@Index(value = {"genreId"})},
        primaryKeys = {"seriesId", "genreId"})
public class SeriesGenreEntity {

    private int seriesId;
    private int genreId;

    public SeriesGenreEntity(int seriesId, int genreId) {
        this.seriesId = seriesId;
        this.genreId = genreId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
