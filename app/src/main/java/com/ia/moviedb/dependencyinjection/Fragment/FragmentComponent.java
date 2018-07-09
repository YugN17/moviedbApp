package com.ia.moviedb.dependencyinjection.Fragment;

import com.ia.moviedb.dependencyinjection.scope.PerFragment;
import com.ia.moviedb.movies.MoviesFragment;
import com.ia.moviedb.series.SeriesFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {FragmentModule.class, FragmentViewModule.class})
public interface FragmentComponent {

    void inject(MoviesFragment moviesFragment);
    void inject(SeriesFragment seriesFragment);

}
