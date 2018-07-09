package com.ia.interactor.series;

import com.ia.SeriesRepository;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import javax.inject.Inject;

public class MakeSeriesFavouriteInteractor extends BaseUseCase<Void> implements Interactor<Integer, Void> {

    private MakeSeriesFavouriteCallback callback;

    private int movieId;

    public interface MakeSeriesFavouriteCallback extends DefaultCallback<Void> {}

    SeriesRepository.MakeSeriesFavouriteCallback dataCallback = new SeriesRepository.MakeSeriesFavouriteCallback() {
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
    public MakeSeriesFavouriteInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, SeriesRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.makeSeriesFavourite(movieId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Integer input, R defaultCallback) {
        this.callback = ((MakeSeriesFavouriteCallback) defaultCallback);
        this.movieId = input;
        executor.execute(this);
    }
}
