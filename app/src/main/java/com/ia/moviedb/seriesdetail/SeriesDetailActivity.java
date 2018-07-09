package com.ia.moviedb.seriesdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ia.entities.Genre;
import com.ia.moviedb.App;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.R;
import com.ia.moviedb.dependencyinjection.activity.ActivityModule;
import com.ia.moviedb.dependencyinjection.application.ViewModule;
import com.ia.moviedb.dependencyinjection.qualifier.ForActivity;
import com.ia.moviedb.model.SeriesViewEntity;
import com.ia.moviedb.moviedetail.ActorAdapter;
import com.ia.moviedb.moviedetail.MovieImageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeriesDetailActivity extends AppCompatActivity implements SeriesDetailView{

    public static final String EXTRA_ID = "id";
    @BindView(R.id.seriesImage)
    ImageView seriesImage;
    @BindView(R.id.seriesBackground)
    ImageView seriesBackground;
    @BindView(R.id.seriesTitle)
    TextView seriesTitle;
    @BindView(R.id.seriesRatingText)
    TextView seriesRating;
    @BindView(R.id.seriesDescription)
    TextView seriesDescription;
    @BindView(R.id.seriesGenres)
    TextView seriesGenres;
    @BindView(R.id.seasonList)
    ExpandableListView seasonList;
    @BindView(R.id.container)
    NestedScrollView container;
    @BindView(R.id.homeprogress)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.seriesCollapsingToolbarLayout)
    CollapsingToolbarLayout seriesCollapsingToolbarLayout;

    @BindView(R.id.seriesImagesRecyclerView)
    RecyclerView imagesList;
    //@Inject
    MovieImageAdapter movieImageAdapter;

    @BindView(R.id.seriesActorRecyclerView)
    RecyclerView actorList;
    //@Inject
    ActorAdapter actorAdapter;
    SeasonEpisodeListAdapter seasonEpisodeListAdapter;


    @Inject
    SeriesDetailPresenter presenter;
    @Inject
    @ForActivity
    Context context;

    @Inject
    ImageLoader imageLoader;

    Palette palette;
    int color;

    public static void actionOpenDetails(Context context, int id, ActivityOptionsCompat options) {
        Intent intent = new Intent(context, SeriesDetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        context.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
        ButterKnife.bind(this);
        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);

        movieImageAdapter = new MovieImageAdapter(context, imageLoader);
        actorAdapter = new ActorAdapter(context, imageLoader);

        setUpAnimation();
        computePalette();
        setSupportActionBar(toolbar);
        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        presenter.onStart(id);
    }

    private void setUpAnimation () {
        supportPostponeEnterTransition();
        seriesImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (seriesImage.getDrawable() != null) {
                    seriesImage.getViewTreeObserver().removeOnPreDrawListener(this);
                    supportStartPostponedEnterTransition();
                    return true;
                }
                return false;
            }
        });
    }

    private void computePalette() {
        seriesBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (seriesBackground.getDrawable() != null) {
                    seriesBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    Palette.from(((BitmapDrawable) seriesBackground.getDrawable()).getBitmap()).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            SeriesDetailActivity.this.palette = palette;
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
    public void showSeries(SeriesViewEntity series) {
        imageLoader.bindImage(series.getPosterImage(), seriesImage);
        imageLoader.bindImage(series.getBackgroundImage(), seriesBackground);
        //movieImage.setImageDrawable(context.getResources().getDrawable(movie.getPosterImage()));
        seriesCollapsingToolbarLayout.setTitle(series.getTitle());
        seriesTitle.setText(series.getTitle());
        seriesRating.setText(String.valueOf(series.getRating()));

        List<Genre> genresList = series.getGenres();
        String genresText = new String();
        for (int i = 0; i < genresList.size(); i++) {
            genresText += genresList.get(i).getName();
            if (i < genresList.size() - 1) genresText += " - ";
        }
        seriesGenres.setText(genresText);

        seriesDescription.setText(series.getOverview());

        imagesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imagesList.setAdapter(movieImageAdapter);
        if (series.getImages() != null) movieImageAdapter.setData(series.getImages());

        actorList.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        actorList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        actorList.setAdapter(actorAdapter);
        if (series.getActorList() != null) actorAdapter.setData(series.getActorList());

        List<String> seasons = new ArrayList<>();
        Map<String, List<String>> episodes = new TreeMap<>();

        seasonEpisodeListAdapter =
                new SeasonEpisodeListAdapter(context, this, seasons, episodes);

        for (Integer i : series.getSeasons().keySet()){
            seasons.add(String.valueOf(i));

            List<String> e = new ArrayList<>();
            for ( Integer j : series.getSeasons().get(i).getEpisodes().keySet()) {
                e.add(String.valueOf(j) + ". " + series.getSeasons().get(i).getEpisodes().get(j));
            }

            episodes.put(String.valueOf(i), e);
        }

        seasonList.setAdapter(seasonEpisodeListAdapter);

        setSeasonListSize();
    }

    private void setSeasonListSize () {
        int listHeight = 0;
        for (int i = 0; i < seasonEpisodeListAdapter.getGroupCount(); i++) {
            View listItem = seasonEpisodeListAdapter.getGroupView(i, false, null, null);
            listItem.measure(0, 0);
            listHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams seasonListLayout = seasonList.getLayoutParams();
        seasonListLayout.height = listHeight + seasonList.getDividerHeight() * (seasonEpisodeListAdapter.getGroupCount() -1);
        seasonList.setLayoutParams(seasonListLayout);
        seasonList.requestLayout();
    }

    @Override
    public void showError(String error) {
        Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

    @Override
    public void setVibrantColor(int color) {
        seriesRating.setTextColor(color);
        seriesCollapsingToolbarLayout.setStatusBarScrimColor(color);
        seriesCollapsingToolbarLayout.setContentScrimColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void onSeasonClicked(int season) {
        int seasonHeight = 0;
        for (int i = 0; i < seasonEpisodeListAdapter.getChildrenCount(season); i++) {
            View listItem = seasonEpisodeListAdapter.getChildView(season, i, false, null, null);
            listItem.measure(0, 0);
            seasonHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams seasonListLayout = seasonList.getLayoutParams();
        seasonListLayout.height += seasonHeight + seasonList.getDividerHeight() * seasonEpisodeListAdapter.getChildrenCount(season);
        seasonList.setLayoutParams(seasonListLayout);
        seasonList.requestLayout();
    }

    @Override
    public void onSeasonClosed(int season) {
        int seasonHeight = 0;
        for (int i = 0; i < seasonEpisodeListAdapter.getChildrenCount(season); i++) {
            View listItem = seasonEpisodeListAdapter.getChildView(season, i, false, null, null);
            listItem.measure(0, 0);
            seasonHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams seasonListLayout = seasonList.getLayoutParams();
        seasonListLayout.height -= seasonHeight + seasonList.getDividerHeight() * seasonEpisodeListAdapter.getChildrenCount(season);
        seasonList.setLayoutParams(seasonListLayout);
        seasonList.requestLayout();
    }
}
