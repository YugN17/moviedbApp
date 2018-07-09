package com.ia.moviedb.dependencyinjection.activity;

import com.ia.moviedb.category.CategoryActivity;
import com.ia.moviedb.dependencyinjection.scope.PerActivity;
import com.ia.moviedb.dependencyinjection.application.ViewModule;
import com.ia.moviedb.moviedetail.MovieDetailActivity;
import com.ia.moviedb.seriesdetail.SeriesDetailActivity;
import com.ia.moviedb.seriesdetail.SeriesDetailView;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModule.class})
public interface ActivityComponent {

    void inject(MovieDetailActivity activity);
    void inject(SeriesDetailActivity activity);
    void inject(CategoryActivity activity);
}
