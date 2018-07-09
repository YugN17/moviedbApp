/*
Created by Helm  31/1/17.
*/


package com.ia.data.repository.datasource;

import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;

import java.io.IOException;
import java.util.List;

public interface ReadOnlyDataSource {

    List<Movie> getMovies(int page) throws IOException;

    Movie getMovieDetails (int movieId) throws IOException;

    List<Series> getSeries(int page) throws IOException;

    Series getSeriesDetails(int seriesId) throws IOException;
}
