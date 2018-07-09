package com.ia.moviedb.series;

import com.ia.entities.series.Series;
import com.ia.exception.ErrorBundle;
import com.ia.interactor.series.GetSeriesInteractor;
import com.ia.interactor.series.MakeSeriesFavouriteInteractor;
import com.ia.interactor.series.RemoveSeriesFavouriteInteractor;

import java.util.List;

import javax.inject.Inject;

public class SeriesPresenter {

    private final SeriesView view;
    private final GetSeriesInteractor getSeriesInteractor;
    private final MakeSeriesFavouriteInteractor makeSeriesFavouriteInteractor;
    private final RemoveSeriesFavouriteInteractor removeSeriesFavouriteInteractor;

    @Inject
    SeriesPresenter(SeriesView view, GetSeriesInteractor getSeriesInteractor, MakeSeriesFavouriteInteractor makeSeriesFavouriteInteractor, RemoveSeriesFavouriteInteractor removeSeriesFavouriteInteractor) {
        this.view = view;
        this.getSeriesInteractor = getSeriesInteractor;
        this.makeSeriesFavouriteInteractor = makeSeriesFavouriteInteractor;
        this.removeSeriesFavouriteInteractor = removeSeriesFavouriteInteractor;
    }


    void onStart(Integer page) {
        getSeriesInteractor.execute(page, new GetSeriesInteractor.GetSeriesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                view.showError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Series> returnParam) {
                view.showData(returnParam);
            }
        });
    }

    public void ShowProgressBar () {
        view.ShowProgressBar();
    }

    public void setFavourite(final int id) {
        makeSeriesFavouriteInteractor.execute(id, new MakeSeriesFavouriteInteractor.MakeSeriesFavouriteCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {

            }

            @Override
            public void onSuccess(Void returnParam) {
                view.reload(id);
            }
        });
    }

    public void setNormal(final int id) {
        removeSeriesFavouriteInteractor.execute(id, new RemoveSeriesFavouriteInteractor.RemoveSeriesFavouriteCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {

            }

            @Override
            public void onSuccess(Void returnParam) {
                view.reload(id);
            }
        });
    }
    void loadMore (Integer page ){
        getSeriesInteractor.execute(page, new GetSeriesInteractor.GetSeriesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {

            }

            @Override
            public void onSuccess(List<Series> returnParam) {
                view.loadMore(returnParam);
            }
        });
    }

}
