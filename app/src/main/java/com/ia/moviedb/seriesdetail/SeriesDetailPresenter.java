package com.ia.moviedb.seriesdetail;


import com.ia.entities.series.Series;
import com.ia.exception.ErrorBundle;
import com.ia.interactor.series.GetSeriesDetailsInteractor;
import com.ia.moviedb.Mapper;

import javax.inject.Inject;

public class SeriesDetailPresenter {

    private final SeriesDetailView seriesDetailView;
    private final GetSeriesDetailsInteractor getSeriesDetailsInteractor;

    @Inject
    public SeriesDetailPresenter(SeriesDetailView seriesDetailView, GetSeriesDetailsInteractor getSeriesDetailsInteractor) {
        this.seriesDetailView = seriesDetailView;
        this.getSeriesDetailsInteractor = getSeriesDetailsInteractor;
    }


    public void updateVibrantColor(int color) {
        seriesDetailView.setVibrantColor(color);
    }

    public void onStart(int id) {
        getSeriesDetailsInteractor.execute(id, new GetSeriesDetailsInteractor.GetSeriesDetailsCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                seriesDetailView.showError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Series returnParam) {
                seriesDetailView.showSeries(Mapper.map(returnParam));
            }
        });
    }
}
