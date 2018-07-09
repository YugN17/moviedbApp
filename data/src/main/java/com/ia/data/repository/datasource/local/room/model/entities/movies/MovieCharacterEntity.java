package com.ia.data.repository.datasource.local.room.model.entities.movies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.ia.data.repository.datasource.local.room.model.entities.ActorEntity;

@Entity(foreignKeys = {@ForeignKey(entity = MovieEntity.class, parentColumns = "id", childColumns = "movieId"),
                        @ForeignKey(entity = ActorEntity.class, parentColumns = "id", childColumns = "actorId")},
        indices = {@Index(value = {"actorId"})},
        primaryKeys = {"movieId", "actorId"})
public class MovieCharacterEntity {

    private String name;
    private int movieId;
    private int actorId;
    private int order;

    public MovieCharacterEntity(String name, int movieId, int actorId, int order) {
        this.name = name;
        this.movieId = movieId;
        this.actorId = actorId;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
