package com.ia.data.repository.datasource;


import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;

import java.io.IOException;
import java.util.List;

public interface ReadWriteDataSource extends ReadOnlyDataSource{

    void writeMovies(List<Movie> movieList) throws IOException;

    void writeMovieDetails(Movie movie) throws IOException;

    void writeSeries(List<Series> seriesList) throws IOException;

    void writeSeriesDetails(Series series) throws IOException;

    void makeMovieFavourite(int movieId) throws IOException;

    void removeMovieFavourite(int movieId) throws IOException;

    boolean checkMovieFavourite (int movieId) throws IOException;

    void makeSeriesFavourite(int seriesId) throws IOException;

    void removeSeriesFavourite(int seriesId) throws IOException;

    boolean checkSeriesFavourite (int seriesId) throws IOException;
}
