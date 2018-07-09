package com.ia.data.repository.datasource.local.room.model.daos.movies;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieEntity;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovie (MovieEntity movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieList(List<MovieEntity> movies);

    @Query("SELECT * FROM MovieEntity")
    public List<MovieEntity> getMovieList();

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    public MovieEntity getMovie(int id);

    @Query("UPDATE MovieEntity SET favourite = 1 WHERE id = :id")
    public void makeFavourite(int id);

    @Query("UPDATE MovieEntity SET favourite = 0 WHERE id = :id")
    public void removeFavourite(int id);

    @Query("SELECT favourite FROM MovieEntity WHERE id = :id")
    public boolean checkFavourite(int id);
}
