package com.ia.moviedb.seriesdetail;


import com.ia.moviedb.model.SeriesViewEntity;

public interface SeriesDetailView {

    void showSeries (SeriesViewEntity seriesViewEntity);
    void showError (String error);
    void setVibrantColor(int color);
    void onSeasonClicked(int season);
    void onSeasonClosed(int season);
}
