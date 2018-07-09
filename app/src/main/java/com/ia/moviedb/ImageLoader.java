package com.ia.moviedb;

import android.widget.ImageView;
import android.widget.ProgressBar;


public interface ImageLoader {

    void bindImage(String image, ImageView view);
    void bindImage(String image, ImageView view, ProgressBar progressBar);
}
