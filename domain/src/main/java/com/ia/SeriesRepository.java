package com.ia;

import com.ia.entities.series.Series;
import com.ia.interactor.DefaultCallback;

import java.util.List;

public interface SeriesRepository {

    interface GetSeriesCallback extends DefaultCallback<List<Series>> {}

    interface GetSeriesDetailsCallback extends DefaultCallback<Series> {}

    interface MakeSeriesFavouriteCallback extends DefaultCallback<Void> {};

    interface RemoveSeriesFavouriteCallback extends DefaultCallback<Void> {};

    void getSeries(Integer page, GetSeriesCallback callback);

    void getSeriesDetails (int seriesId, GetSeriesDetailsCallback callback);

    void makeSeriesFavourite (int seriesId, SeriesRepository.MakeSeriesFavouriteCallback callback);

    void removeSeriesFavourite (int seriesId, SeriesRepository.RemoveSeriesFavouriteCallback callback);
}
