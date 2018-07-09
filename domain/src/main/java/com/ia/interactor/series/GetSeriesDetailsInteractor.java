package com.ia.interactor.series;

import com.ia.SeriesRepository;
import com.ia.entities.series.Series;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import javax.inject.Inject;


public class GetSeriesDetailsInteractor extends BaseUseCase<Series> implements Interactor<Integer, Series> {

    private int seriesId;

    public interface GetSeriesDetailsCallback extends DefaultCallback<Series> {}

    SeriesRepository.GetSeriesDetailsCallback dataCallback = new SeriesRepository.GetSeriesDetailsCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Series returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final SeriesRepository repository;
    private final ThreadExecutor executor;
    private GetSeriesDetailsCallback callback;

    @Inject
    public GetSeriesDetailsInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, SeriesRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getSeriesDetails(seriesId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Series>> void execute(Integer id, R defaultCallback) {
        this.seriesId = id;
        this.callback = ((GetSeriesDetailsCallback) defaultCallback);
        executor.execute(this);
    }
}
