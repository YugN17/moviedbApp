package com.ia.moviedb.dependencyinjection.Fragment;

import com.ia.moviedb.movies.MoviesView;
import com.ia.moviedb.series.SeriesView;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentViewModule {

    private MoviesView moviesView;
    private SeriesView seriesView;

    public FragmentViewModule(MoviesView moviesView) {
        this.moviesView = moviesView;
    }

    public FragmentViewModule(SeriesView seriesView) {
        this.seriesView = seriesView;
    }

    @Provides
    MoviesView providesMoviesView(){
        return moviesView;
    }

    @Provides
    SeriesView providesSeriesView(){
        return seriesView;
    }
}
