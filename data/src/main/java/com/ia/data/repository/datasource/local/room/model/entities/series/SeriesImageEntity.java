package com.ia.data.repository.datasource.local.room.model.entities.series;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = SeriesEntity.class, parentColumns = "id", childColumns = "seriesId"),
        primaryKeys = {"seriesId", "image"})
public class SeriesImageEntity {

    private int seriesId;
    @NonNull
    private String image;
    private int order;

    public SeriesImageEntity(int seriesId, @NonNull String image, int order) {
        this.seriesId = seriesId;
        this.image = image;
        this.order = order;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
