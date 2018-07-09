package com.ia.moviedb.model;


import com.ia.entities.Actor;
import com.ia.entities.Genre;

import java.util.List;
import java.util.Map;

public class SeriesViewEntity {

    private Integer id;
    private String title;
    private String overview;
    private String posterImage;
    private String backgroundImage;
    private float rating;
    private List<String> images;
    private List<Actor> actorList;
    private List<Genre> genres;
    private Map<Integer, SeasonViewEntity> seasons; //Season number, season info

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public Map<Integer, SeasonViewEntity> getSeasons() {
        return seasons;
    }

    public void setSeasons(Map<Integer, SeasonViewEntity> seasons) {
        this.seasons = seasons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
