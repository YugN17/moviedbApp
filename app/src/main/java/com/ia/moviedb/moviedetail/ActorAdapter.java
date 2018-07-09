package com.ia.moviedb.moviedetail;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleixballetbo.customviews.SimpleCustomView;
import com.ia.entities.Actor;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.R;
import com.ia.moviedb.dependencyinjection.qualifier.ForActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder>{

    private final Context context;
    private List<Actor> actorList;
    private final ImageLoader imageLoader;

    @Inject
    public ActorAdapter (@ForActivity Context context, ImageLoader imageLoader) {
        actorList = new ArrayList<>();
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_item, parent, false);
        return new ActorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorAdapter.ViewHolder holder, int position) {
        String image = actorList.get(position).getImage();
        imageLoader.bindImage(image, holder.actorImage);

        holder.name.setText(actorList.get(position).getName());
        holder.character.setText(actorList.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public void setData (List<Actor> actorList) {
        this.actorList = actorList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actorImage)
        SimpleCustomView actorImage;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.role)
        TextView character;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
