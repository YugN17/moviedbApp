package com.ia.moviedb.moviedetail;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.R;
import com.ia.moviedb.dependencyinjection.qualifier.ForActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieImageAdapter extends RecyclerView.Adapter<MovieImageAdapter.ViewHolder>{

    private final Context context;
    private List<String> movieImages;
    private final ImageLoader imageLoader;

    @Inject
    public MovieImageAdapter (@ForActivity Context context, ImageLoader imageLoader) {
        movieImages = new ArrayList<>();
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public MovieImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_image_item, parent, false);
        return new MovieImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieImageAdapter.ViewHolder holder, int position) {
        String image = movieImages.get(position);
        imageLoader.bindImage(image, holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieImages.size();
    }

    public void setData (List<String> movieImages) {
        this.movieImages = movieImages;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemImage)
        ImageView movieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
