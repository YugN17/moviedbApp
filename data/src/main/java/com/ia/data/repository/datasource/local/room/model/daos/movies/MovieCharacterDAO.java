package com.ia.data.repository.datasource.local.room.model.daos.movies;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieCharacterEntity;

import java.util.List;

@Dao
public interface MovieCharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCharacter (MovieCharacterEntity movieCharacterEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCharacterList (List<MovieCharacterEntity> movieCharacterEntityList);

    @Query("SELECT * FROM MovieCharacterEntity WHERE movieId = :movieId ORDER BY `order`")
    public List<MovieCharacterEntity> getCharactersFromMovie (int movieId);
}
