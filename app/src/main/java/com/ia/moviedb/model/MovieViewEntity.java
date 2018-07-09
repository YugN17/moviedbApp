package com.ia.moviedb.model;


import com.ia.entities.Actor;
import com.ia.entities.Genre;

import java.util.List;

public class MovieViewEntity {

    private String title;
    private String overview;
    private String posterImage;
    private String backgroundImage;
    private float rating;
    private List<Genre> genres;
    private List<String> images;
    private List<Actor> actorList;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public float getRating() {
        return rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<String> getImages () {
        return images;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }
}
