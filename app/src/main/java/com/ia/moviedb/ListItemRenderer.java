package com.ia.moviedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ia.entities.movies.Movie;
import com.pedrogomez.renderers.Renderer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListItemRenderer extends Renderer<Movie> {

    @BindView(R.id.itemImage)
    public ImageView itemImage;

    @BindView(R.id.itemTitle)
    TextView itemTitle;

    @BindView(R.id.item_rating)
    TextView item_rating;

    @BindView(R.id.loadingListPoster)
    ProgressBar progressBar;

    @BindView(R.id.fav_button)
    protected ImageButton fav_button;

    private ImageLoader imageLoader;
    public int id;

    public ListItemRenderer(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void render() {
        Movie movie = getContent();
        imageLoader.bindImage(movie.getPosterImage(), itemImage, progressBar);
        itemTitle.setText(movie.getTitle());
        item_rating.setText(String.valueOf(movie.getRating()));
        id = getContent().getId();
    }
}
