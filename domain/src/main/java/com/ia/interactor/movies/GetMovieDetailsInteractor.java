package com.ia.interactor.movies;

import com.ia.MovieRepository;
import com.ia.entities.movies.Movie;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import javax.inject.Inject;

public class GetMovieDetailsInteractor extends BaseUseCase<Movie> implements Interactor<Integer, Movie> {

    private GetMovieDetailsCallback callback;

    private int movieId;

    public interface GetMovieDetailsCallback extends DefaultCallback<Movie> {}

    MovieRepository.GetMovieDetailsCallback dataCallback = new MovieRepository.GetMovieDetailsCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Movie returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final MovieRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public GetMovieDetailsInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, MovieRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getMovieDetails(movieId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Movie>> void execute(Integer input, R defaultCallback) {
        this.callback = ((GetMovieDetailsCallback) defaultCallback);
        this.movieId = input;
        executor.execute(this);
    }
}
