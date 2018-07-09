package com.ia.interactor.movies;

import com.ia.MovieRepository;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import javax.inject.Inject;

public class MakeMovieFavouriteInteractor extends BaseUseCase<Void> implements Interactor<Integer, Void> {

    private MakeMovieFavouriteCallback callback;

    private int movieId;

    public interface MakeMovieFavouriteCallback extends DefaultCallback<Void> {}

    private MovieRepository.MakeMovieFavouriteCallback dataCallback = new MovieRepository.MakeMovieFavouriteCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Void returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final MovieRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public MakeMovieFavouriteInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, MovieRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.makeMovieFavourite(movieId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Integer input, R defaultCallback) {
        this.callback = ((MakeMovieFavouriteCallback) defaultCallback);
        this.movieId = input;
        executor.execute(this);
    }
}
