package com.ia.data.repository.datasource.local.room.model.daos.movies;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieGenreEntity;

import java.util.List;

@Dao
public interface MovieGenreDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieGenre (MovieGenreEntity movieGenreEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieGenreList (List<MovieGenreEntity> movieGenreEntity);

    @Query("SELECT * FROM MovieGenreEntity WHERE movieId = :movieId")
    public List<MovieGenreEntity> getGenresFromMovie (int movieId);
}
