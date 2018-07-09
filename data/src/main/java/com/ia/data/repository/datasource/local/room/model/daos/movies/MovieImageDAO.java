package com.ia.data.repository.datasource.local.room.model.daos.movies;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieImageEntity;

import java.util.List;

@Dao
public interface MovieImageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieImage (MovieImageEntity movieImageEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieImageList (List<MovieImageEntity> movieImageEntityList);

    @Query("SELECT * FROM MovieImageEntity WHERE movieId = :movieId ORDER BY `order`")
    public List<MovieImageEntity> getImagesFromMovie (int movieId);
}
