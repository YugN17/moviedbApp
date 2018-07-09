package com.ia.entities.movies;

import com.ia.entities.Actor;
import com.ia.entities.Genre;

import java.util.List;

public class Movie {

    private String posterImage;
    private String backgroundImage;
    private String title;
    private int id;
    private String overview;
    private float rating;
    private List<Genre> genres;
    private List<String> movieImageList;
    private List<Actor> actorList;
    private Boolean favourite;


    public Movie(String posterImage, String backgroundImage, String title, int id, String overview, float rating, List<Genre> genres, List<String> movieImageList, List<Actor> actorList) {
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
        this.title = title;
        this.id = id;
        this.overview = overview;
        this.rating = rating;
        this.genres = genres;
        this.movieImageList = movieImageList;
        this.actorList = actorList;
    }

    public Movie(int id, String title, String overview, String posterImage, String backgroundImage, float rating, List<Genre> genres, boolean favourite) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
        this.rating = rating;
        this.genres = genres;
        this.favourite = favourite;
    }

    public Movie(int id, String title, String overview, String posterImage, String backgroundImage, float rating, boolean favourite) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
        this.rating = rating;
        this.favourite = favourite;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
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

    public List<String> getMovieImageList() {
        return movieImageList;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setMovieImageList(List<String> movieImageList) {
        this.movieImageList = movieImageList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }
}
