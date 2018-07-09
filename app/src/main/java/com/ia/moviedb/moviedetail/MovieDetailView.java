package com.ia.moviedb.moviedetail;

import com.ia.moviedb.model.MovieViewEntity;

public interface MovieDetailView {

    void showMovie(MovieViewEntity movie);

    void showError(String error);

    void setVibrantColor(int color);
}
