package com.ia.data.repository.datasource.local.room.model.entities.movies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.ia.data.repository.datasource.local.room.model.entities.GenreEntity;

@Entity(foreignKeys = {@ForeignKey(entity = MovieEntity.class, parentColumns = "id", childColumns = "movieId"),
        @ForeignKey(entity = GenreEntity.class, parentColumns = "id", childColumns = "genreId")},
        indices = {@Index(value = {"genreId"})},
        primaryKeys = {"movieId", "genreId"})
public class MovieGenreEntity {

    private int movieId;
    private int genreId;

    public MovieGenreEntity(int movieId, int genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
