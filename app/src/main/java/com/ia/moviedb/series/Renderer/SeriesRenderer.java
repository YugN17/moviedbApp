package com.ia.moviedb.series.Renderer;


import android.app.Activity;
import android.support.v4.app.ActivityOptionsCompat;

import com.ia.entities.movies.Movie;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.ListItemRenderer;
import com.ia.moviedb.R;
import com.ia.moviedb.series.SeriesPresenter;
import com.ia.moviedb.seriesdetail.SeriesDetailActivity;

import butterknife.OnClick;

class SeriesRenderer extends ListItemRenderer {

    private SeriesPresenter presenter;

    SeriesRenderer(ImageLoader imageLoader, SeriesPresenter presenter) {
        super(imageLoader);
        this.presenter = presenter;
    }

    @OnClick(R.id.itemImage)
    void OnSeriesClicked (){
        Movie series = getContent();
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(((Activity) getContext()),itemImage , "movieImage");
        SeriesDetailActivity.actionOpenDetails(getContext(), series.getId(), options);
        presenter.ShowProgressBar();
    }

    @OnClick(R.id.fav_button)
    void OnFavClicked() {
        presenter.setFavourite(id);
    }

}
