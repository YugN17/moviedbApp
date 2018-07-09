package com.ia.data.repository.datasource.local.room.model.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ia.data.repository.datasource.local.room.model.entities.ActorEntity;

import java.util.List;

@Dao
public interface ActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertActor (ActorEntity actorEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertActorList (List<ActorEntity> actorEntityList);

    @Query("SELECT * FROM ActorEntity WHERE id = :id")
    public ActorEntity getActor (int id);
}
