package com.ia.data.repository.datasource.local.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ia.data.repository.datasource.local.room.model.daos.ActorDAO;
import com.ia.data.repository.datasource.local.room.model.daos.GenreDAO;
import com.ia.data.repository.datasource.local.room.model.daos.movies.MovieCharacterDAO;
import com.ia.data.repository.datasource.local.room.model.daos.movies.MovieDAO;
import com.ia.data.repository.datasource.local.room.model.daos.movies.MovieGenreDAO;
import com.ia.data.repository.datasource.local.room.model.daos.movies.MovieImageDAO;
import com.ia.data.repository.datasource.local.room.model.daos.series.SeriesCharacterDAO;
import com.ia.data.repository.datasource.local.room.model.daos.series.SeriesDAO;
import com.ia.data.repository.datasource.local.room.model.daos.series.SeriesEpisodeDAO;
import com.ia.data.repository.datasource.local.room.model.daos.series.SeriesGenreDAO;
import com.ia.data.repository.datasource.local.room.model.daos.series.SeriesImageDAO;
import com.ia.data.repository.datasource.local.room.model.daos.series.SeriesSeasonDAO;
import com.ia.data.repository.datasource.local.room.model.entities.ActorEntity;
import com.ia.data.repository.datasource.local.room.model.entities.GenreEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieCharacterEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieGenreEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieImageEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesCharacterEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEpisodeEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesGenreEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesImageEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesSeasonEntity;

@Database(entities = {MovieEntity.class, ActorEntity.class, MovieCharacterEntity.class, GenreEntity.class, MovieGenreEntity.class, MovieImageEntity.class,
        SeriesEntity.class, SeriesCharacterEntity.class, SeriesEpisodeEntity.class, SeriesGenreEntity.class, SeriesImageEntity.class, SeriesSeasonEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract MovieDAO movieDAO();
    public abstract ActorDAO actorDAO();
    public abstract MovieCharacterDAO movieCharacterDAO();
    public abstract GenreDAO genreDAO();
    public abstract MovieGenreDAO movieGenreDAO();
    public abstract MovieImageDAO movieImageDAO();

    public abstract SeriesDAO seriesDAO();
    public abstract SeriesCharacterDAO seriesCharacterDAO();
    public abstract SeriesEpisodeDAO seriesEpisodeDAO();
    public abstract SeriesGenreDAO seriesGenreDAO();
    public abstract SeriesImageDAO seriesImageDAO();
    public abstract SeriesSeasonDAO seriesSeasonDAO();
}
