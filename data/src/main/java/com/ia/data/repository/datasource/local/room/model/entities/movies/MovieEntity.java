package com.ia.data.repository.datasource.local.room.model.entities.movies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MovieEntity {

    @PrimaryKey
    private int id;
    private String title;
    private String overview;
    private String posterImage;
    private String backgroundImage;
    private float rating;
    private boolean favourite;
    //private List<GenreEntity> genres;
    //private List<String> movieImageList;
    //private List<ActorEntity> actorList;

    public MovieEntity(int id, String title, String overview, String posterImage, String backgroundImage, float rating, boolean favourite) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
        this.rating = rating;
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
