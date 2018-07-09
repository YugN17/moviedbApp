package com.ia.data.repository.datasource.local.room.model.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.GenreEntity;

import java.util.List;

@Dao
public interface GenreDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGenre (GenreEntity genreEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGenreList (List<GenreEntity> genreEntityList);

    @Query("SELECT * FROM GenreEntity WHERE id = :id")
    public GenreEntity getGenre (int id);
}
