package com.ia.data.repository.datasource.cloud.model.series;

import com.google.gson.annotations.SerializedName;
import com.ia.data.repository.datasource.cloud.model.GenreDTO;

import java.util.List;

public class SerieDTO {
    private int id;
    @SerializedName("poster_path")
    private String posterImage;
    @SerializedName("backdrop_path")
    private String backgroundImage;
    @SerializedName("original_name")
    private String title;
    private String overview;
    private float vote_average;
    private List<GenreDTO> genres;
    private List<SeriesSeasonDTO> seasons;

    /*
    public SerieDTO(int id, String posterImage, String title, String overview, List<SeriesSeasonDTO> seasons) {
        this.id = id;
        this.posterImage = posterImage;
        this.title = title;
        this.overview = overview;
        this.seasons = seasons;
    }
    */

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

    /*
    public List<SeriesSeasonDTO> getSeasons() {
        return seasons;
    }
    */

    public float getRating() {
        return vote_average;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public List<SeriesSeasonDTO> getSeasons() {
        return seasons;
    }
}
