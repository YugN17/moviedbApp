package com.ia.entities.series;

import com.ia.entities.Actor;
import com.ia.entities.Genre;

import java.util.List;

public class Series {
    private int id;
    private String posterImage;
    private String backgroundImage;
    private String title;
    private String overview;
    private List<SeriesSeason> seasons;
    private float rating;
    private Boolean favourite;
    private List<Genre> genres;
    private List<String> seriesImageList;
    private List<Actor> actorList;

    public Series(int id, String posterImage, String title, String overview, List<SeriesSeason> seasons) {
        this.id = id;
        this.posterImage = posterImage;
        this.title = title;
        this.overview = overview;
        this.seasons = seasons;
    }

    public Series(int id, String posterImage, String title, String overview, float rating, Boolean favourite) {
        this.id = id;
        this.posterImage = posterImage;
        this.title = title;
        this.overview = overview;
        this.rating = rating;
        this.favourite = favourite;
    }

    public Series(int id, String title, String overview, String posterImage, String backgroundImage, float rating, List<Genre> genreList, List<SeriesSeason> seriesSeasonList, boolean favourite) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
        this.rating = rating;
        this.genres = genreList;
        this.seasons = seriesSeasonList;
        this.favourite = favourite;
    }

    public Series(int id, String title, String overview, String posterImage, String backgroundImage, float rating, boolean favourite) {
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

    public String getPosterImage() {
        return posterImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public List<SeriesSeason> getSeasons() {
        return seasons;
    }

    public float getRating() {
        return rating;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public List<String> getSeriesImageList() {
        return seriesImageList;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setSeriesImageList(List<String> seriesImageList) {
        this.seriesImageList = seriesImageList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public void setSeasons(List<SeriesSeason> seasons) {
        this.seasons = seasons;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
