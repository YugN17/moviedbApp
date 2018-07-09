package com.ia.moviedb.moviedetail;

import com.ia.entities.movies.Movie;
import com.ia.exception.ErrorBundle;
import com.ia.interactor.movies.GetMovieDetailsInteractor;
import com.ia.moviedb.Mapper;

import javax.inject.Inject;

public class MovieDetailPresenter {

    private final MovieDetailView movieDetailView;
    private final GetMovieDetailsInteractor getMovieDetailsInteractor;

    @Inject
    public MovieDetailPresenter(MovieDetailView movieDetailView, GetMovieDetailsInteractor getMovieDetailsInteractor) {
        this.movieDetailView = movieDetailView;
        this.getMovieDetailsInteractor = getMovieDetailsInteractor;
    }

    public void onStart(int id) {
        getMovieDetailsInteractor.execute(id, new GetMovieDetailsInteractor.GetMovieDetailsCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                movieDetailView.showError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Movie returnParam) {
                movieDetailView.showMovie(Mapper.map(returnParam));
            }
        });
    }

    public void updateVibrantColor (int vibrantColor) {
        movieDetailView.setVibrantColor(vibrantColor);
    }
}
