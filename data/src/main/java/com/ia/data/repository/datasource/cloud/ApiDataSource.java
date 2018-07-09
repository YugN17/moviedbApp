package com.ia.data.repository.datasource.cloud;

import android.util.Log;

import com.ia.data.repository.datasource.ReadOnlyDataSource;
import com.ia.data.repository.datasource.cloud.model.ActorCollectionDTO;
import com.ia.data.repository.datasource.cloud.model.ImageCollectionDTO;
import com.ia.data.repository.datasource.cloud.model.Mapper;
import com.ia.data.repository.datasource.cloud.model.movies.MovieDTO;
import com.ia.data.repository.datasource.cloud.model.movies.MoviesDTO;
import com.ia.data.repository.datasource.cloud.model.series.SerieDTO;
import com.ia.data.repository.datasource.cloud.model.series.SeriesDTO;
import com.ia.data.repository.datasource.cloud.model.series.SeriesSeasonDTO;
import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;
import com.ia.entities.series.SeriesSeason;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class ApiDataSource implements ReadOnlyDataSource {

    private final MovieService movieService;
    private final SeriesService seriesService;

    @Inject
    ApiDataSource(MovieService movieService, SeriesService seriesService) {
        this.movieService = movieService;
        this.seriesService = seriesService;
    }

    @Override
    public List<Movie> getMovies(int page) throws IOException {
        Response<MoviesDTO> moviesCallResponse = movieService.discoverMovies(page).execute();
        Log.i("tag", moviesCallResponse.toString());
        if (!moviesCallResponse.isSuccessful()) {
            throw new IOException("We cannot load the movie list. Please try again later");
        }
        return Mapper.map(moviesCallResponse.body().getResult());
    }

    @Override
    public Movie getMovieDetails(int movieId) throws IOException {

        Response<MovieDTO> movieDetailsCallResponse = movieService.getMovieDetails(movieId).execute();
        Response<ImageCollectionDTO> movieImageCollectionResponse = movieService.getMovieImages(movieId).execute();
        Response<ActorCollectionDTO> actorCollectionResponse = movieService.getMovieCharacters(movieId).execute();

        if (!movieDetailsCallResponse.isSuccessful() || !movieImageCollectionResponse.isSuccessful() || !actorCollectionResponse.isSuccessful()) {
            throw new IOException("We cannot load this movie. Please try again later");
        }

        Movie movie = Mapper.map(movieDetailsCallResponse.body());

        ImageCollectionDTO imageCollectionDTO = movieImageCollectionResponse.body();
        movie.setMovieImageList(Mapper.mapImages(imageCollectionDTO.getResults()));

        ActorCollectionDTO actorCollectionDTO = actorCollectionResponse.body();
        movie.setActorList(Mapper.mapActors(actorCollectionDTO.getResults()));

        return movie;
    }

    @Override
    public List<Series> getSeries(int page) throws IOException {
        Response<SeriesDTO> seriesCallResponse = seriesService.discoverSeries(page).execute();
        if (!seriesCallResponse.isSuccessful()) {
            throw new IOException("We cannot load the series list. Please try again later");
        }
        return Mapper.mapSeries(seriesCallResponse.body().getResult());
    }

    @Override
    public Series getSeriesDetails(int seriesId) throws IOException {
        Response<SerieDTO> seriesDetailsCallResponse = seriesService.getSeriesDetails(seriesId).execute();
        Response<ImageCollectionDTO> seriesImageCollectionResponse = seriesService.getSeriesImages(seriesId).execute();
        Response<ActorCollectionDTO> seriesActorCollectionResponse = seriesService.getSeriesCharacters(seriesId).execute();

        if (!seriesDetailsCallResponse.isSuccessful() || !seriesImageCollectionResponse.isSuccessful() || !seriesActorCollectionResponse.isSuccessful()) {
            throw new IOException("We cannot load this series. Please try again later");
        }

        Series series = Mapper.map(seriesDetailsCallResponse.body());

        ImageCollectionDTO imageCollectionDTO = seriesImageCollectionResponse.body();
        series.setSeriesImageList(Mapper.mapImages(imageCollectionDTO.getResults()));

        ActorCollectionDTO actorCollectionDTO = seriesActorCollectionResponse.body();
        series.setActorList(Mapper.mapActors(actorCollectionDTO.getResults()));

        List<SeriesSeason> seriesSeasonList = new ArrayList<>();
        for (SeriesSeason season : series.getSeasons()) {
            //seriesSeasonList.add(getSeriesSeasonDetails(seriesId, season.getSeasonNumber()));
            Response<SeriesSeasonDTO> seriesSeasonCallResponse = seriesService.getSeriesSeasonDetails(seriesId, season.getSeasonNumber()).execute();
            if (!seriesSeasonCallResponse.isSuccessful()) {
                throw new IOException("We cannot load the episodes for season " + season.getSeasonNumber() + ". Please try again later");
            }
            seriesSeasonList.add(Mapper.map(seriesSeasonCallResponse.body()));
        }
        series.setSeasons(seriesSeasonList);

        return series;
    }
}
