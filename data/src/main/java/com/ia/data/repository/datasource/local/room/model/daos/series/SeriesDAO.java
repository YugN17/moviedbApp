package com.ia.data.repository.datasource.local.room.model.daos.series;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEntity;

import java.util.List;

@Dao
public interface SeriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeries (SeriesEntity series);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesList (List<SeriesEntity> seriesList);

    @Query("SELECT * FROM SeriesEntity")
    public List<SeriesEntity> getSeriesList ();

    @Query("SELECT * FROM SeriesEntity WHERE id = :id")
    public SeriesEntity getSeries(int id);

    @Query("UPDATE SeriesEntity SET favourite = 1 WHERE id = :id")
    public void makeFavourite(int id);

    @Query("UPDATE SeriesEntity SET favourite = 0 WHERE id = :id")
    public void removeFavourite(int id);

    @Query("SELECT favourite FROM SeriesEntity WHERE id = :id")
    public boolean checkFavourite(int id);
}
