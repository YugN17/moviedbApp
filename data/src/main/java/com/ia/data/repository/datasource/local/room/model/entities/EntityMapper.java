package com.ia.data.repository.datasource.local.room.model.entities;


import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieImageEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEpisodeEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesImageEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesSeasonEntity;
import com.ia.entities.Actor;
import com.ia.entities.Genre;
import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;
import com.ia.entities.series.SeriesEpisode;
import com.ia.entities.series.SeriesSeason;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper {

    //entity --> domain

    public static Movie mapMovieEntity(MovieEntity movieEntity) {
        return new Movie(movieEntity.getId(), movieEntity.getTitle(), movieEntity.getOverview(), movieEntity.getPosterImage(), movieEntity.getBackgroundImage(), movieEntity.getRating(), movieEntity.isFavourite());
    }

    public static Series mapSeriesEntity(SeriesEntity seriesEntity) {
        return new Series(seriesEntity.getId(), seriesEntity.getTitle(), seriesEntity.getOverview(), seriesEntity.getPosterImage(), seriesEntity.getBackgroundImage(), seriesEntity.getRating(), seriesEntity.isFavourite());
    }

    public static List<Movie> mapMovieEntityList(List<MovieEntity> movieEntityList) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieEntityList.size(); i++) {
            movieList.add(mapMovieEntity(movieEntityList.get(i)));
        }
        return movieList;
    }

    public static List<Series> mapSeriesEntityList(List<SeriesEntity> seriesEntityList) {
        List<Series> seriesList = new ArrayList<>();
        for (int i = 0; i < seriesEntityList.size(); i++) {
            seriesList.add(mapSeriesEntity(seriesEntityList.get(i)));
        }
        return seriesList;
    }

    public static Genre mapGenreEntity(GenreEntity genreEntity) {
        return new Genre(genreEntity.getId(), genreEntity.getName());
    }

    public static List<Genre> mapGenreEntityList(List<GenreEntity> genreEntityList) {
        List<Genre> genreList = new ArrayList<>();
        for (int i = 0; i < genreEntityList.size(); i++) {
            genreList.add(mapGenreEntity(genreEntityList.get(i)));
        }
        return genreList;
    }

    public static List<String> mapMovieImageEntityList(List<MovieImageEntity> movieImageEntityList) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < movieImageEntityList.size(); i++) {
            imageList.add(movieImageEntityList.get(i).getImage());
        }
        return imageList;
    }

    public static List<String> mapSeriesImageEntityList(List<SeriesImageEntity> seriesImageEntityList) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < seriesImageEntityList.size(); i++) {
            imageList.add(seriesImageEntityList.get(i).getImage());
        }
        return imageList;
    }

    public static Actor mapActorEntity(String characterName, ActorEntity actor) {
        return new Actor(actor.getId(), characterName, actor.getName(), actor.getImage());
    }

    public static SeriesSeason mapSeriesSeasonEntity(SeriesSeasonEntity seriesSeasonEntity) {
        return new SeriesSeason(seriesSeasonEntity.getId(), seriesSeasonEntity.getSeasonNumber(), seriesSeasonEntity.getEpisodeCount());
    }

    public static List<SeriesSeason> mapSeriesSeasonEntityList(List<SeriesSeasonEntity> seriesSeasonEntityList) {
        List<SeriesSeason> seriesSeasonList = new ArrayList<>();
        for (int i = 0; i < seriesSeasonEntityList.size(); i++) {
            seriesSeasonList.add(mapSeriesSeasonEntity(seriesSeasonEntityList.get(i)));
        }
        return seriesSeasonList;
    }

    public static SeriesEpisode mapSeriesEpisodeEntity (SeriesEpisodeEntity seriesEpisodeEntity) {
        return new SeriesEpisode(seriesEpisodeEntity.getId(), seriesEpisodeEntity.getEpisodeNumber(), seriesEpisodeEntity.getName());
    }

    public static List<SeriesEpisode> mapSeriesEpisodeEntityList(List<SeriesEpisodeEntity> seriesEpisodeEntityList) {
        List<SeriesEpisode> seriesEpisodeList = new ArrayList<>();
        for (int i = 0; i < seriesEpisodeEntityList.size(); i++) {
            seriesEpisodeList.add(mapSeriesEpisodeEntity(seriesEpisodeEntityList.get(i)));
        }
        return seriesEpisodeList;
    }

    //domain --> entity

    public static MovieEntity mapMovie(Movie movie) {
        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getOverview(), movie.getPosterImage(), movie.getBackgroundImage(), movie.getRating(), movie.getFavourite());
    }

    public static SeriesEntity mapSeries(Series series) {
        return new SeriesEntity(series.getId(), series.getTitle(), series.getOverview(), series.getPosterImage(), series.getBackgroundImage(), series.getRating(), series.getFavourite());
    }

    public static List<MovieEntity> mapMovieList(List<Movie> movieList) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            movieEntityList.add(mapMovie(movieList.get(i)));
        }
        return movieEntityList;
    }

    public static List<SeriesEntity> mapSeriesList(List<Series> seriesList) {
        List<SeriesEntity> seriesEntityList = new ArrayList<>();
        for (int i = 0; i < seriesList.size(); i++) {
            seriesEntityList.add(mapSeries(seriesList.get(i)));
        }
        return seriesEntityList;
    }

    public static GenreEntity mapGenre(Genre genre) {
        return new GenreEntity(genre.getId(), genre.getName());
    }

    public static List<GenreEntity> mapGenreList(List<Genre> genres) {
        List<GenreEntity> genreEntityList = new ArrayList<>();
        for (int i = 0; i < genres.size(); i++) {
            genreEntityList.add(mapGenre(genres.get(i)));
        }
        return genreEntityList;
    }

    public static MovieImageEntity mapMovieImage(int movieId, String image, int order) {
        return new MovieImageEntity(movieId, image, order);
    }

    public static List<MovieImageEntity> mapMovieImageList(int movieId, List<String> movieImageList) {
        List<MovieImageEntity> movieImageEntityList = new ArrayList<>();
        for (int i = 0; i < movieImageList.size() && i < 11; i++) {
            movieImageEntityList.add(mapMovieImage(movieId, movieImageList.get(i), i));
        }
        return movieImageEntityList;
    }

    public static SeriesImageEntity mapSeriesImage(int seriesId, String image, int order) {
        return new SeriesImageEntity(seriesId, image, order);
    }

    public static List<SeriesImageEntity> mapSeriesImageList(int seriesId, List<String> seriesImageList) {
        List<SeriesImageEntity> seriesImageEntityList = new ArrayList<>();
        for (int i = 0; i < seriesImageList.size() && i < 11; i++) {
            seriesImageEntityList.add(mapSeriesImage(seriesId, seriesImageList.get(i), i));
        }
        return seriesImageEntityList;
    }

    public static ActorEntity mapActor(Actor actor) {
        return new ActorEntity(actor.getId(), actor.getName(), actor.getImage());
    }

    public static List<ActorEntity> mapActorList(List<Actor> actorList) {
        List<ActorEntity> actorEntityList = new ArrayList<>();
        for (int i = 0; i < actorList.size() && i < 10; i++) {
            actorEntityList.add(mapActor(actorList.get(i)));
        }
        return actorEntityList;
    }

    public static SeriesSeasonEntity mapSeriesSeason(int seriesId, SeriesSeason seriesSeason) {
        return new SeriesSeasonEntity(seriesSeason.getId(), seriesId, seriesSeason.getSeasonNumber(), seriesSeason.getEpisodeCount());
    }

    public static List<SeriesSeasonEntity> mapSeriesSeasonList(int seriesId, List<SeriesSeason> seriesSeasonList) {
        List<SeriesSeasonEntity> seriesSeasonEntityList = new ArrayList<>();
        for (int i = 0; i < seriesSeasonList.size(); i++) {
            seriesSeasonEntityList.add(mapSeriesSeason(seriesId, seriesSeasonList.get(i)));
        }
        return seriesSeasonEntityList;
    }

    public static SeriesEpisodeEntity mapSeriesEpisode (int seriesId, int seasonNumber, SeriesEpisode seriesEpisode) {
        return new SeriesEpisodeEntity(seriesEpisode.getId(), seriesEpisode.getEpisodeNumber(), seasonNumber, seriesId, seriesEpisode.getName());
    }

    public static List<SeriesEpisodeEntity> mapSeriesEpisodeList(int seriesId, int seasonNumber, List<SeriesEpisode> seriesEpisodeList) {
        List<SeriesEpisodeEntity> seriesEpisodeEntityList = new ArrayList<>();
        for (int i = 0; i < seriesEpisodeList.size(); i++) {
            seriesEpisodeEntityList.add(mapSeriesEpisode(seriesId, seasonNumber, seriesEpisodeList.get(i)));
        }
        return seriesEpisodeEntityList;
    }
}
