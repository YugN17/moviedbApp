package com.ia.data.repository.datasource.local.room.model.daos.series;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesCharacterEntity;

import java.util.List;

@Dao
public interface SeriesCharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCharacter(SeriesCharacterEntity seriesCharacterEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCharacterList(List<SeriesCharacterEntity> seriesCharacterEntityList);

    @Query("SELECT * FROM SeriesCharacterEntity WHERE seriesId = :seriesId ORDER BY `order`")
    public List<SeriesCharacterEntity> getCharactersFromSeries(int seriesId);
}
