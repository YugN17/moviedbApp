package com.ia;

import com.ia.entities.movies.Movie;
import com.ia.interactor.DefaultCallback;

import java.util.List;

public interface MovieRepository {

    interface GetMoviesCallback extends DefaultCallback<List<Movie>> {}

    interface GetMovieDetailsCallback extends DefaultCallback<Movie> {}

    interface MakeMovieFavouriteCallback extends DefaultCallback<Void> {}

    interface RemoveMovieFavouriteCallback extends DefaultCallback<Void> {}

    void getMovies(int page, GetMoviesCallback callback);

    void getMovieDetails (int movieId, GetMovieDetailsCallback callback);

    void makeMovieFavourite (int movieId, MakeMovieFavouriteCallback callback);

    void removeMovieFavourite (int movieId, RemoveMovieFavouriteCallback callback);
}