package com.ia.interactor.movies;


import com.ia.MovieRepository;
import com.ia.entities.movies.Movie;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import java.util.List;

import javax.inject.Inject;

public class GetMoviesInteractor extends BaseUseCase<List<Movie>> implements Interactor<Integer, List<Movie>> {

    private GetMoviesCallback callback;
    Integer page;

    public interface GetMoviesCallback extends DefaultCallback<List<Movie>> {}

    MovieRepository.GetMoviesCallback dataCallback = new MovieRepository.GetMoviesCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<Movie> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final MovieRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public GetMoviesInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, MovieRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getMovies(page, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<Movie>>> void execute(Integer input, R callback) {
        this.callback = ((GetMoviesCallback) callback);
        this.page = input;
        executor.execute(this);
    }
}
