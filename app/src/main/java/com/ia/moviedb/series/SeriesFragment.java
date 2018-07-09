package com.ia.moviedb.series;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.ia.data.dependencyinjection.ForApp;
import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;
import com.ia.moviedb.App;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.R;
import com.ia.moviedb.category.FragmentInteractor;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentModule;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentViewModule;
import com.ia.moviedb.series.Renderer.SeriesRendererBuilder;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeriesFragment extends Fragment implements SeriesView{

    @Inject
    public SeriesPresenter seriesPresenter;
    @Inject
    public ImageLoader imageLoader;

    @ForApp
    @Inject
    Context context;

    View rootView;
    private FragmentInteractor fragmentInteractor;

    @BindView(R.id.seriesList)
    RecyclerView seriesList;
    @BindView(R.id.series_progress_bar)
    ProgressBar progressBar;

    private RVRendererAdapter<Movie> adapter;

    int page;
    boolean isLoading;
    boolean lastPage;

    List<Movie> series;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity()
                .getApplication())
                .getComponent()
                .plus(new FragmentModule(this), new FragmentViewModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_series, container, false);
        ButterKnife.bind(this, rootView);
        page = 1;
        isLoading = true;
        lastPage = false;
        seriesPresenter.onStart(page);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractor) {
            fragmentInteractor = (FragmentInteractor) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void setUpRecyclerView() {
        seriesList.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.column_count)));
        seriesList.setAdapter(adapter);
        seriesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int count = linearLayoutManager.getItemCount();
                int last = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && count <= (last + 4)) {
                    isLoading = true;
                    if (!lastPage) {
//                   rootView.setAlpha((float) 0.45);
                        progressBar.getBackground().setAlpha((int) 0.45);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    seriesPresenter.loadMore(page);
                }

            }
        });
    }

    @Override
    public void showData(List<Series> tvShows) {
        series = new ArrayList<>();
        for (Series s : tvShows) series.add(
                new Movie(s.getId(), s.getTitle(), null, s.getPosterImage(),null, s.getRating(), null, s.getFavourite()));
        adapter = new RVRendererAdapter<>(new SeriesRendererBuilder(imageLoader, seriesPresenter), new ListAdapteeCollection<>(series));
        setUpRecyclerView();
        isLoading = false;
        page ++;
    }

    @Override
    public void showError(String error) {
        Toast toast = Toast.makeText(context, error, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void ShowProgressBar() {
        fragmentInteractor.OnItemSelected();
    }

    @Override
    public void loadMore(List<Series> returnParam) {
        if ( returnParam!= null ) {
            lastPage = false;
            for (Series s : returnParam)
                series.add(
                        new Movie(s.getId(), s.getTitle(), null, s.getPosterImage(), null, s.getRating(), null, s.getFavourite()));
            adapter.setCollection(new ListAdapteeCollection<>(series));
            adapter.notifyDataSetChanged();
            page ++;
        }
        else lastPage = true;
        isLoading = false;
//        rootView.setAlpha(1);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void reload(int id) {
        for (Movie m: series) if(m.getId() == id) m.setFavourite(!m.getFavourite());
        adapter.notifyDataSetChanged();
    }
}
