package com.ia.data.repository.datasource.local.room.model.daos.series;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesSeasonEntity;

import java.util.List;

@Dao
public interface SeriesSeasonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesSeason (SeriesSeasonEntity seriesSeasonEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesSeasonList (List<SeriesSeasonEntity> seriesSeasonEntityList);

    @Query("SELECT * FROM SeriesSeasonEntity WHERE seriesId = :seriesId")
    public List<SeriesSeasonEntity> getSeriesSeasonsFromSeries(int seriesId);

    @Query("SELECT * FROM SeriesSeasonEntity WHERE seriesId = :seriesId AND seasonNumber = :seasonNumber")
    public SeriesSeasonEntity getSeriesSeason (int seriesId, int seasonNumber);
}
