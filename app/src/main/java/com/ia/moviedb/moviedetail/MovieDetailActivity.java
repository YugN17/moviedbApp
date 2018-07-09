package com.ia.moviedb.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ia.entities.Genre;
import com.ia.moviedb.App;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.R;
import com.ia.moviedb.dependencyinjection.activity.ActivityModule;
import com.ia.moviedb.dependencyinjection.application.ViewModule;
import com.ia.moviedb.dependencyinjection.qualifier.ForActivity;
import com.ia.moviedb.model.MovieViewEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    public static final String EXTRA_ID = "id";
    @BindView(R.id.itemImage)
    ImageView movieImage;
    @BindView(R.id.movieBackground)
    ImageView movieBackground;
    @BindView(R.id.itemTitle)
    TextView movieTitle;
    @BindView(R.id.ratingText)
    TextView rating;
    @BindView(R.id.movieDescription)
    TextView movieDescription;
    @BindView(R.id.genres)
    TextView genres;

    @BindView(R.id.imagesRecyclerView)
    RecyclerView imagesList;
    @Inject
    MovieImageAdapter movieImageAdapter;

    @BindView(R.id.actorRecyclerView)
    RecyclerView actorList;
    @Inject
    ActorAdapter actorAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Inject
    MovieDetailPresenter presenter;
    @Inject
    @ForActivity
    Context context;

    @Inject
    ImageLoader imageLoader;

    Palette palette;
    int color;

    public static void actionOpenDetails(Context context, int id, ActivityOptionsCompat options) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        context.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);
        setUpAnimation();
        computePalette();
        setSupportActionBar(toolbar);
        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        presenter.onStart(id);
    }

    private void setUpAnimation () {
        supportPostponeEnterTransition();
        movieImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (movieImage.getDrawable() != null) {
                    movieImage.getViewTreeObserver().removeOnPreDrawListener(this);
                    supportStartPostponedEnterTransition();
                    return true;
                }
                return false;
            }
        });
    }

    private void computePalette() {
        movieBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (movieBackground.getDrawable() != null) {
                    movieBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    Palette.from(((BitmapDrawable) movieBackground.getDrawable()).getBitmap()).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            MovieDetailActivity.this.palette = palette;
                            int defaultColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                            color = palette.getVibrantColor(defaultColor);
                            presenter.updateVibrantColor(color);
                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showMovie(MovieViewEntity movie){
        imageLoader.bindImage(movie.getPosterImage(), movieImage);
        imageLoader.bindImage(movie.getBackgroundImage(), movieBackground);
        //movieImage.setImageDrawable(context.getResources().getDrawable(movie.getPosterImage()));
        collapsingToolbarLayout.setTitle(movie.getTitle());
        movieTitle.setText(movie.getTitle());
        rating.setText(String.valueOf(movie.getRating()));

        List<Genre> genresList = movie.getGenres();
        String genresText = new String();
        for (int i = 0; i < genresList.size(); i++) {
            genresText += genresList.get(i).getName();
            if (i < genresList.size() - 1) genresText += " - ";
        }
        genres.setText(genresText);

        movieDescription.setText(movie.getOverview());

        imagesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imagesList.setAdapter(movieImageAdapter);
        movieImageAdapter.setData(movie.getImages());

        actorList.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        actorList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        actorList.setAdapter(actorAdapter);
        actorAdapter.setData(movie.getActorList());
    }

    @Override
    public void setVibrantColor(int color) {
        rating.setTextColor(color);
        collapsingToolbarLayout.setStatusBarScrimColor(color);
        collapsingToolbarLayout.setContentScrimColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void showError (String error) {
        Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

}
