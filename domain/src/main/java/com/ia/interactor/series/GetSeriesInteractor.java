package com.ia.interactor.series;

import com.ia.SeriesRepository;
import com.ia.entities.series.Series;
import com.ia.exception.ErrorBundle;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.interactor.BaseUseCase;
import com.ia.interactor.DefaultCallback;
import com.ia.interactor.Interactor;

import java.util.List;

import javax.inject.Inject;

public class GetSeriesInteractor extends BaseUseCase<List<Series>> implements Interactor<Integer, List<Series>> {

    public interface GetSeriesCallback extends DefaultCallback<List<Series>> {}
    private Integer page;

    private SeriesRepository.GetSeriesCallback dataCallback = new SeriesRepository.GetSeriesCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<Series> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final SeriesRepository repository;
    private final ThreadExecutor executor;
    private GetSeriesCallback callback;

    @Inject
    public GetSeriesInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, SeriesRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
            repository.getSeries(page, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<Series>>> void execute(Integer param, R callback) {
            this.callback = (GetSeriesCallback) callback;
            this.page = param;
            executor.execute(this);
    }
}
