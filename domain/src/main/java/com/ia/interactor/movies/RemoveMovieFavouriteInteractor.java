package com.ia.interactor.movies;

import com.ia.MovieRepository;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import javax.inject.Inject;

public class RemoveMovieFavouriteInteractor extends BaseUseCase<Void> implements Interactor<Integer, Void> {

    private RemoveMovieFavouriteCallback callback;

    private int movieId;

    public interface RemoveMovieFavouriteCallback extends DefaultCallback<Void> {}

    private MovieRepository.RemoveMovieFavouriteCallback dataCallback = new MovieRepository.RemoveMovieFavouriteCallback() {
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
    public RemoveMovieFavouriteInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, MovieRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.removeMovieFavourite(movieId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Integer input, R defaultCallback) {
        this.callback = ((RemoveMovieFavouriteCallback) defaultCallback);
        this.movieId = input;
        executor.execute(this);
    }
}
