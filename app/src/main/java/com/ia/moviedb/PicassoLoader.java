package com.ia.moviedb;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ia.data.dependencyinjection.ForApp;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;


public class PicassoLoader implements ImageLoader {

    private final Context context;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original/";

    @Inject
    public PicassoLoader(@ForApp Context context) {
        this.context = context;
    }


    /*
    @Override
    public void bindImage(String image, ImageView view) {
        Picasso.with(context).load(BASE_IMAGE_URL + image).placeholder(R.drawable.progress_animation).fit().into(view);
        view.invalidate();
    }
    */

    @Override
    public void bindImage(String image, ImageView view) {
        Picasso.with(context).load(BASE_IMAGE_URL + image).fit().into(view);
        view.invalidate();
    }
    @Override
    public void bindImage(String image, ImageView view, final ProgressBar progressBar) {
        Picasso.with(context).load(BASE_IMAGE_URL + image).fit().into(view, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        view.invalidate();
    }
}
