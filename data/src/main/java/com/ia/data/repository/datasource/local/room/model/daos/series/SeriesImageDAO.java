package com.ia.data.repository.datasource.local.room.model.daos.series;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesImageEntity;

import java.util.List;

@Dao
public interface SeriesImageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesImage(SeriesImageEntity seriesImageEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesImageList(List<SeriesImageEntity> seriesImageEntityList);

    @Query("SELECT * FROM SeriesImageEntity WHERE seriesId = :seriesId ORDER BY `order`")
    public List<SeriesImageEntity> getImagesFromSeries(int seriesId);
}
