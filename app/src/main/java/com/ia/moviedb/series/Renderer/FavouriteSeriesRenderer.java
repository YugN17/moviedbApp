package com.ia.moviedb.series.Renderer;


import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.series.SeriesPresenter;

public class FavouriteSeriesRenderer extends SeriesRenderer {

    private SeriesPresenter presenter;

    FavouriteSeriesRenderer(ImageLoader imageLoader, SeriesPresenter presenter) {
        super(imageLoader, presenter);
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        fav_button.setImageDrawable(getContext().getDrawable(android.R.drawable.btn_star_big_on));
    }

    @Override
    void OnFavClicked() {
        presenter.setNormal(id);
    }
}
