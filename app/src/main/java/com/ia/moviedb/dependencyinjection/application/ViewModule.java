package com.ia.moviedb.dependencyinjection.application;

import com.ia.moviedb.category.CategoryView;
import com.ia.moviedb.moviedetail.MovieDetailView;
import com.ia.moviedb.seriesdetail.SeriesDetailActivity;
import com.ia.moviedb.seriesdetail.SeriesDetailView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {


    private MovieDetailView movieDetailView;
    private SeriesDetailView seriesDetailView;
    private CategoryView categoryView;

    public ViewModule(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
    }

    public ViewModule(SeriesDetailView seriesDetailView) {
        this.seriesDetailView = seriesDetailView;
    }

    public ViewModule(CategoryView categoryView) {
        this.categoryView = categoryView;
    }



    @Provides
    MovieDetailView providesMovieDetailView(){
        return movieDetailView;
    }

    @Provides
    SeriesDetailView providesSeriesDetailView(){
        return seriesDetailView;
    }


    @Provides
    CategoryView providesCategoryView () {
        return categoryView;
    }
}
