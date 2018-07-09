package com.ia.moviedb.series;

import com.ia.entities.series.Series;

import java.util.List;

public interface SeriesView {

    void showData(List<Series> tvShows);

    void showError (String error);

    void ShowProgressBar();

    void reload(int id);

    void loadMore(List<Series> returnParam);
}
