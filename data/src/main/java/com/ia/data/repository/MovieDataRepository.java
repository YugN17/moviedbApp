package com.ia.data.repository;


import android.content.Context;

import com.ia.MovieRepository;
import com.ia.data.dependencyinjection.ForApp;
import com.ia.data.repository.datasource.ReadOnlyDataSource;
import com.ia.data.repository.datasource.ReadWriteDataSource;
import com.ia.entities.movies.Movie;
import com.ia.exception.ErrorBundle;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class MovieDataRepository implements MovieRepository {

    private final ReadOnlyDataSource readOnlyDataSource;
    private final ReadWriteDataSource readWriteDataSource;
    private final Context context;

    @Inject
    MovieDataRepository(ReadOnlyDataSource readDataSource, ReadWriteDataSource readWriteDataSource, @ForApp Context context) {
        this.readOnlyDataSource = readDataSource;
        this.readWriteDataSource = readWriteDataSource;
        this.context = context;
    }

    @Override
    public void getMovies(int page, MovieRepository.GetMoviesCallback callback) {
        try {
            List<Movie> movieList;
            if (ConnectivityChecker.isNetworkAvailable(context)) {
                movieList = readOnlyDataSource.getMovies(page);
                for (Movie movie : movieList) {
                    if (readWriteDataSource.checkMovieFavourite(movie.getId())) {
                        movie.setFavourite(true);
                    }
                }
                readWriteDataSource.writeMovies(movieList);
            }
            else {
                movieList = readWriteDataSource.getMovies(page);
            }
            callback.onSuccess(movieList);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void getMovieDetails(int movieId, MovieRepository.GetMovieDetailsCallback callback) {
        try {
            Movie movie;
            if (ConnectivityChecker.isNetworkAvailable(context)) {
                movie = readOnlyDataSource.getMovieDetails(movieId);
                if (readWriteDataSource.checkMovieFavourite(movie.getId())) {
                    movie.setFavourite(true);
                }
                readWriteDataSource.writeMovieDetails(movie);
            }
            else {
                movie = readWriteDataSource.getMovieDetails(movieId);
            }
            callback.onSuccess(movie);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void makeMovieFavourite(int movieId, MakeMovieFavouriteCallback callback) {
        try {
            readWriteDataSource.makeMovieFavourite(movieId);
            callback.onSuccess(null);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void removeMovieFavourite(int movieId, RemoveMovieFavouriteCallback callback) {
        try {
            readWriteDataSource.removeMovieFavourite(movieId);
            callback.onSuccess(null);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }
}
