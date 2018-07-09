package com.ia.moviedb.movies;

import com.ia.entities.movies.Movie;
import com.ia.exception.ErrorBundle;
import com.ia.interactor.movies.GetMoviesInteractor;
import com.ia.interactor.movies.MakeMovieFavouriteInteractor;
import com.ia.interactor.movies.RemoveMovieFavouriteInteractor;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter {
    private final MoviesView view;
    private final GetMoviesInteractor getMoviesInteractor;
    private final MakeMovieFavouriteInteractor makeMovieFavouriteInteractor;
    private final RemoveMovieFavouriteInteractor removeMovieFavouriteInteractor;

    @Inject
    MoviesPresenter(MoviesView view, GetMoviesInteractor getMoviesInteractor, MakeMovieFavouriteInteractor makeMovieFavouriteInteractor, RemoveMovieFavouriteInteractor removeMovieFavouriteInteractor) {
        this.view = view;
        this.getMoviesInteractor = getMoviesInteractor;
        this.makeMovieFavouriteInteractor = makeMovieFavouriteInteractor;
        this.removeMovieFavouriteInteractor = removeMovieFavouriteInteractor;
    }


    void onStart(Integer page) {
        getMoviesInteractor.execute(page, new GetMoviesInteractor.GetMoviesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                view.showError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Movie> returnParam) {
                view.showData(returnParam);
            }
        });
    }

    public void ShowProgressBar () {
        view.ShowProgressBar();
    }

    void loadMore(int page) {
        getMoviesInteractor.execute(page, new GetMoviesInteractor.GetMoviesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {

            }

            @Override
            public void onSuccess(List<Movie> returnParam) {
                view.loadMore(returnParam);
            }
        });
    }

    public void setFavourite(final int id) {
        makeMovieFavouriteInteractor.execute(id, new MakeMovieFavouriteInteractor.MakeMovieFavouriteCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {

            }

            @Override
            public void onSuccess(Void returnParam) {
                view.reload(id);
            }
        });
    }

    public void setNormal(final int id) {
        removeMovieFavouriteInteractor.execute(id, new RemoveMovieFavouriteInteractor.RemoveMovieFavouriteCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {

            }

            @Override
            public void onSuccess(Void returnParam) {
                view.reload(id);
            }
        });
    }
}
