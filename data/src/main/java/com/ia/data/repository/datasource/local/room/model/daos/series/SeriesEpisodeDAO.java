package com.ia.data.repository.datasource.local.room.model.daos.series;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEpisodeEntity;

import java.util.List;

@Dao
public interface SeriesEpisodeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesEpisode(SeriesEpisodeEntity seriesEpisodeEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSeriesEpisodeList(List<SeriesEpisodeEntity> seriesEpisodeEntityList);

    @Query("SELECT * FROM SeriesEpisodeEntity WHERE seriesId = :seriesId AND seasonNumber = :seasonNumber")
    public List<SeriesEpisodeEntity> getEpisodesFromSeriesSeason(int seriesId, int seasonNumber);
}
