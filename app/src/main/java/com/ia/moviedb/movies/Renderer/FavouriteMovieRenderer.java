package com.ia.moviedb.movies.Renderer;


import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.movies.MoviesPresenter;

public class FavouriteMovieRenderer extends MovieRenderer {

    private MoviesPresenter presenter;

    FavouriteMovieRenderer(ImageLoader imageLoader, MoviesPresenter presenter) {
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
