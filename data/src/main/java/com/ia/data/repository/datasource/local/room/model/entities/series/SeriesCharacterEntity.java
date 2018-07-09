package com.ia.data.repository.datasource.local.room.model.entities.series;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.ia.data.repository.datasource.local.room.model.entities.ActorEntity;

@Entity(foreignKeys = {@ForeignKey(entity = SeriesEntity.class, parentColumns = "id", childColumns = "seriesId"),
                        @ForeignKey(entity = ActorEntity.class, parentColumns = "id", childColumns = "actorId")},
        indices = {@Index(value = {"actorId"})},
        primaryKeys = {"seriesId", "actorId"})
public class SeriesCharacterEntity {

    private String name;
    private int seriesId;
    private int actorId;
    private int order;

    public SeriesCharacterEntity(String name, int seriesId, int actorId, int order) {
        this.name = name;
        this.seriesId = seriesId;
        this.actorId = actorId;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
