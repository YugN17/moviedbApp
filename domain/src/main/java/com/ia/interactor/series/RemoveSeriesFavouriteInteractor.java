package com.ia.interactor.series;

import com.ia.SeriesRepository;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import javax.inject.Inject;

public class RemoveSeriesFavouriteInteractor extends BaseUseCase<Void> implements Interactor<Integer, Void> {

    private RemoveSeriesFavouriteCallback callback;

    private int movieId;

    public interface RemoveSeriesFavouriteCallback extends DefaultCallback<Void> {}

    SeriesRepository.RemoveSeriesFavouriteCallback dataCallback = new SeriesRepository.RemoveSeriesFavouriteCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Void returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final SeriesRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public RemoveSeriesFavouriteInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, SeriesRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.removeSeriesFavourite(movieId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Integer input, R defaultCallback) {
        this.callback = ((RemoveSeriesFavouriteCallback) defaultCallback);
        this.movieId = input;
        executor.execute(this);
    }
}
