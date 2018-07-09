package com.ia.data.repository.datasource.local;

import com.ia.data.repository.datasource.ReadWriteDataSource;
import com.ia.data.repository.datasource.local.room.AppDatabase;
import com.ia.data.repository.datasource.local.room.model.entities.EntityMapper;
import com.ia.data.repository.datasource.local.room.model.entities.GenreEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieCharacterEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieGenreEntity;
import com.ia.data.repository.datasource.local.room.model.entities.movies.MovieImageEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesCharacterEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesGenreEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesImageEntity;
import com.ia.data.repository.datasource.local.room.model.entities.series.SeriesSeasonEntity;
import com.ia.entities.Actor;
import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;
import com.ia.entities.series.SeriesSeason;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RoomDataSource implements ReadWriteDataSource {

    private AppDatabase database;

    @Inject
    RoomDataSource(AppDatabase database) {
        this.database = database;
    }

    @Override
    public List<Movie> getMovies(int page) throws IOException {
        if (page > 1) {
            return null;
        }
        List<MovieEntity> movieEntityList = database.movieDAO().getMovieList();
        if (movieEntityList.isEmpty()) {
            throw new IOException("We cannot load the movie list. Please try again when internet connection is available");
        }
        return EntityMapper.mapMovieEntityList(movieEntityList);
    }

    @Override
    public Movie getMovieDetails(int movieId) throws IOException {
        Movie movie = EntityMapper.mapMovieEntity(database.movieDAO().getMovie(movieId));

        List<MovieGenreEntity> movieGenreEntityList = database.movieGenreDAO().getGenresFromMovie(movieId);
        if (movieGenreEntityList.isEmpty()) {
            throw new IOException("We cannot load this movie. Please try again when internet connection is available");
        }
        List<GenreEntity> genreEntityList = new ArrayList<>();
        for (int i = 0; i < movieGenreEntityList.size(); i++) {
            int genreId = movieGenreEntityList.get(i).getGenreId();
            genreEntityList.add(database.genreDAO().getGenre(genreId));
        }
        movie.setGenres(EntityMapper.mapGenreEntityList(genreEntityList));

        List<MovieImageEntity> movieImageEntityList = database.movieImageDAO().getImagesFromMovie(movieId);
        movie.setMovieImageList(EntityMapper.mapMovieImageEntityList(movieImageEntityList));

        List<MovieCharacterEntity> characterEntities = database.movieCharacterDAO().getCharactersFromMovie(movieId);
        List<Actor> actorList = new ArrayList<>();
        for (int i = 0; i < characterEntities.size(); i++) {
            int actorId = characterEntities.get(i).getActorId();
            String characterName = characterEntities.get(i).getName();
            actorList.add(EntityMapper.mapActorEntity(characterName, database.actorDAO().getActor(actorId)));
        }
        movie.setActorList(actorList);

        return movie;
    }

    @Override
    public List<Series> getSeries(int page) throws IOException {
        if (page > 1) {
            return null;
        }
        List<SeriesEntity> seriesEntityList = database.seriesDAO().getSeriesList();
        if (seriesEntityList.isEmpty()) {
            throw new IOException("We cannot load the series list. Please try again when internet connection is available");
        }
        return EntityMapper.mapSeriesEntityList(seriesEntityList);
    }

    @Override
    public Series getSeriesDetails(int seriesId) throws IOException {
        Series series = EntityMapper.mapSeriesEntity(database.seriesDAO().getSeries(seriesId));

        List<SeriesGenreEntity> seriesGenreEntityList = database.seriesGenreDAO().getGenresFromSeries(seriesId);
        if (seriesGenreEntityList.isEmpty()) {
            throw new IOException("We cannot load this movie. Please try again when internet connection is available");
        }
        List<GenreEntity> genreEntityList = new ArrayList<>();
        for (int i = 0; i < seriesGenreEntityList.size(); i++) {
            int genreId = seriesGenreEntityList.get(i).getGenreId();
            genreEntityList.add(database.genreDAO().getGenre(genreId));
        }
        series.setGenres(EntityMapper.mapGenreEntityList(genreEntityList));

        List<SeriesImageEntity> seriesImageEntityList = database.seriesImageDAO().getImagesFromSeries(seriesId);
        series.setSeriesImageList(EntityMapper.mapSeriesImageEntityList(seriesImageEntityList));

        List<SeriesCharacterEntity> seriesCharacterEntityList = database.seriesCharacterDAO().getCharactersFromSeries(seriesId);
        List<Actor> actorList = new ArrayList<>();
        for (int i = 0; i < seriesCharacterEntityList.size(); i++) {
            int actorId = seriesCharacterEntityList.get(i).getActorId();
            String characterName = seriesCharacterEntityList.get(i).getName();
            actorList.add(EntityMapper.mapActorEntity(characterName, database.actorDAO().getActor(actorId)));
        }
        series.setActorList(actorList);

        List<SeriesSeasonEntity> seriesSeasonEntityList = database.seriesSeasonDAO().getSeriesSeasonsFromSeries(seriesId);
        series.setSeasons(EntityMapper.mapSeriesSeasonEntityList(seriesSeasonEntityList));

        List<SeriesSeason> seriesSeasonList = new ArrayList<>();
        for (SeriesSeason season : series.getSeasons()) {
            season.setEpisodes(EntityMapper.mapSeriesEpisodeEntityList(database.seriesEpisodeDAO().getEpisodesFromSeriesSeason(seriesId, season.getSeasonNumber())));
            seriesSeasonList.add(season);
        }
        series.setSeasons(seriesSeasonList);

        return series;
    }

    @Override
    public void writeMovies(List<Movie> movieList) throws IOException{
        database.movieDAO().insertMovieList(EntityMapper.mapMovieList(movieList));
    }

    @Override
    public void writeMovieDetails(Movie movie) throws IOException{
        database.movieDAO().insertMovie(EntityMapper.mapMovie(movie));

        database.genreDAO().insertGenreList(EntityMapper.mapGenreList(movie.getGenres()));

        List<MovieGenreEntity> movieGenreEntityList = new ArrayList<>();
        for (int i = 0; i < movie.getGenres().size(); i++) {
            MovieGenreEntity movieGenreEntity = new MovieGenreEntity(movie.getId(), movie.getGenres().get(i).getId());
            movieGenreEntityList.add(movieGenreEntity);
        }
        database.movieGenreDAO().insertMovieGenreList(movieGenreEntityList);

        database.movieImageDAO().insertMovieImageList(EntityMapper.mapMovieImageList(movie.getId(), movie.getMovieImageList()));

        database.actorDAO().insertActorList(EntityMapper.mapActorList(movie.getActorList()));

        List<MovieCharacterEntity> movieCharacterEntityList = new ArrayList<>();
        for (int i = 0; i < movie.getActorList().size() && i < 10; i++) {
            Actor actor = movie.getActorList().get(i);
            MovieCharacterEntity movieCharacterEntity = new MovieCharacterEntity(actor.getCharacter(), movie.getId(), actor.getId(), i);
            movieCharacterEntityList.add(movieCharacterEntity);
        }
        database.movieCharacterDAO().insertCharacterList(movieCharacterEntityList);
    }

    @Override
    public void writeSeries(List<Series> seriesList) throws IOException{
        database.seriesDAO().insertSeriesList(EntityMapper.mapSeriesList(seriesList));
    }

    @Override
    public void writeSeriesDetails(Series series) throws IOException{
        database.seriesDAO().insertSeries(EntityMapper.mapSeries(series));

        database.genreDAO().insertGenreList(EntityMapper.mapGenreList(series.getGenres()));

        List<SeriesGenreEntity> seriesGenreEntityList = new ArrayList<>();
        for (int i = 0; i < series.getGenres().size(); i++) {
            SeriesGenreEntity seriesGenreEntity = new SeriesGenreEntity(series.getId(), series.getGenres().get(i).getId());
            seriesGenreEntityList.add(seriesGenreEntity);
        }
        database.seriesGenreDAO().insertSeriesGenreList(seriesGenreEntityList);

        database.seriesImageDAO().insertSeriesImageList(EntityMapper.mapSeriesImageList(series.getId(), series.getSeriesImageList()));

        database.actorDAO().insertActorList(EntityMapper.mapActorList(series.getActorList()));

        List<SeriesCharacterEntity> seriesCharacterEntityList = new ArrayList<>();
        for (int i = 0; i < series.getActorList().size() && i < 10; i++) {
            Actor actor = series.getActorList().get(i);
            SeriesCharacterEntity seriesCharacterEntity = new SeriesCharacterEntity(actor.getCharacter(), series.getId(), actor.getId(), i);
            seriesCharacterEntityList.add(seriesCharacterEntity);
        }
        database.seriesCharacterDAO().insertCharacterList(seriesCharacterEntityList);

        database.seriesSeasonDAO().insertSeriesSeasonList(EntityMapper.mapSeriesSeasonList(series.getId(), series.getSeasons()));

        for (SeriesSeason season : series.getSeasons()) {
            database.seriesEpisodeDAO().insertSeriesEpisodeList(EntityMapper.mapSeriesEpisodeList(series.getId(), season.getSeasonNumber(), season.getEpisodes()));
        }
    }

    @Override
    public void makeMovieFavourite(int movieId) throws IOException{
        database.movieDAO().makeFavourite(movieId);
    }

    @Override
    public void removeMovieFavourite(int movieId) throws IOException {
        database.movieDAO().removeFavourite(movieId);
    }

    @Override
    public boolean checkMovieFavourite(int movieId) throws IOException {
        return database.movieDAO().checkFavourite(movieId);
    }

    @Override
    public void makeSeriesFavourite(int seriesId) throws IOException {
        database.seriesDAO().makeFavourite(seriesId);
    }

    @Override
    public void removeSeriesFavourite(int seriesId) throws IOException {
        database.seriesDAO().removeFavourite(seriesId);
    }

    @Override
    public boolean checkSeriesFavourite(int seriesId) throws IOException {
        return database.seriesDAO().checkFavourite(seriesId);
    }
}
