package com.ia.moviedb.movies.Renderer;


import android.app.Activity;
import android.support.v4.app.ActivityOptionsCompat;

import com.ia.entities.movies.Movie;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.ListItemRenderer;
import com.ia.moviedb.R;
import com.ia.moviedb.moviedetail.MovieDetailActivity;
import com.ia.moviedb.movies.MoviesPresenter;

import butterknife.OnClick;

class MovieRenderer extends ListItemRenderer {

    private MoviesPresenter presenter;

    MovieRenderer(ImageLoader imageLoader, MoviesPresenter presenter) {
        super(imageLoader);
        this.presenter = presenter;
    }

    @OnClick(R.id.itemImage)
    void OnMovieClicked (){
        Movie movie = getContent();
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(((Activity) getContext()),itemImage , "movieImage");
        MovieDetailActivity.actionOpenDetails(getContext(), movie.getId(), options);
        presenter.ShowProgressBar();
    }

    @OnClick(R.id.fav_button)
    void OnFavClicked() {
        presenter.setFavourite(id);
    }
}
