package com.ia.data.repository.datasource.local.room.model.entities.movies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = MovieEntity.class, parentColumns = "id", childColumns = "movieId"),
        primaryKeys = {"movieId", "image"})
public class MovieImageEntity {

    private int movieId;
    @NonNull
    private String image;
    private int order;

    public MovieImageEntity(int movieId, @NonNull String image, int order) {
        this.movieId = movieId;
        this.image = image;
        this.order = order;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
