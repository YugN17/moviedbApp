package com.ia.data.repository.datasource.cloud.model.movies;

import com.google.gson.annotations.SerializedName;
import com.ia.data.repository.datasource.cloud.model.GenreDTO;

import java.util.List;

public class MovieDTO {

    @SerializedName("poster_path")
    private String posterImage;
    @SerializedName("backdrop_path")
    private String backgroundImage;
    private String title;
    private int id;
    private String overview;
    @SerializedName("vote_average")
    private float rating;
    private List<GenreDTO> genres;
    /*
    private List<ImageDTO> movieImageList;
    private List<ActorDTO> actorList;
    */

    /*
    public MovieEntity(String posterImage, String backgroundImage, String title, int id, String overview, float rating, List<Genre> genres, List<MovieImageEntity> movieImageList) {
        this.posterImage = posterImage;
        this.backgroundImage = backgroundImage;
        this.title = title;
        this.id = id;
        this.overview = overview;
        this.rating = rating;
        this.genres = genres;
        this.movieImageList = movieImageList;
    }
    */

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

    public List<GenreDTO> getGenres() {
        return genres;
    }

    /*
    public List<ImageDTO> getMovieImageList() {
        return movieImageList;
    }

    public List<ActorDTO> getActorList() {
        return actorList;
    }

    public void setSeriesImageList(List<ImageDTO> movieImageList) {
        this.movieImageList = movieImageList;
    }

    public void setActorList(List<ActorDTO> actorList) {
        this.actorList = actorList;
    }
    */
}
