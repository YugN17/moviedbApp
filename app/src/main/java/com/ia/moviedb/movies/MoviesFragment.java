package com.ia.moviedb.movies;

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
import com.ia.moviedb.App;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.R;
import com.ia.moviedb.category.FragmentInteractor;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentModule;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentViewModule;
import com.ia.moviedb.movies.Renderer.MovieRendererBuilder;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements MoviesView {

    @Inject
    public MoviesPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    @ForApp
    @Inject
    Context context;

    @BindView(R.id.movieList)
    RecyclerView movieList;
    @BindView(R.id.movies_progress_bar)
    ProgressBar progressBar;

    View rootView;

    private FragmentInteractor fragmentInteractor;

    private RVRendererAdapter<Movie> adapter;

    int page;
    boolean isLoading;
    boolean lastPage;

    List<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity()
                .getApplication())
                .getComponent()
                .plus(new FragmentModule(this), new FragmentViewModule(this))
                .inject(this);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, rootView);
        page = 1;
        isLoading = true;
        lastPage = false;
        presenter.onStart(page);
        return rootView;
    }

    private void setUpRecyclerView() {
        movieList.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.column_count)));
        movieList.setAdapter(adapter);
        movieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    presenter.loadMore(page);
                }
            }
        });
    }

    @Override
    public void showData(List<Movie> tvShows) {
        movies = tvShows;
        adapter = new RVRendererAdapter<>(new MovieRendererBuilder(imageLoader, presenter), new ListAdapteeCollection<>(movies));
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
    public void loadMore(List<Movie> returnParam) {
        if(returnParam != null) {
            lastPage = false;
            movies.addAll(returnParam);
            adapter.setCollection(new ListAdapteeCollection<>(movies));
            adapter.notifyDataSetChanged();
            page ++;
        }
        else lastPage= true;
        isLoading = false;
//        rootView.setAlpha(1);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void reload(int id) {
        for (Movie m: movies) if(m.getId() == id) m.setFavourite(!m.getFavourite());
        adapter.notifyDataSetChanged();
    }

}
