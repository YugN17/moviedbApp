package com.ia.moviedb.movies;

import com.ia.entities.movies.Movie;

import java.util.List;

public interface MoviesView {

    void showData(List<Movie> tvShows);

    void showError (String error);

    void ShowProgressBar();

    void reload(int id);
    void loadMore(List<Movie> returnParam);
}
