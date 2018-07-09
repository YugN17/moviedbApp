package com.ia.data.repository.datasource.local.room.model.daos.series;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesGenreEntity;

import java.util.List;

@Dao
public interface SeriesGenreDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesGenre(SeriesGenreEntity seriesGenreEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesGenreList(List<SeriesGenreEntity> seriesGenreEntityList);

    @Query("SELECT * FROM SeriesGenreEntity WHERE seriesId = :seriesId")
    public List<SeriesGenreEntity> getGenresFromSeries(int seriesId);
}
