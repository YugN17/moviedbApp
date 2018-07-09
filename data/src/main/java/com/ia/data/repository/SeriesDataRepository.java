package com.ia.data.repository;

import android.content.Context;

import com.ia.SeriesRepository;
import com.ia.data.dependencyinjection.ForApp;
import com.ia.data.repository.datasource.ReadOnlyDataSource;
import com.ia.data.repository.datasource.ReadWriteDataSource;
import com.ia.entities.series.Series;
import com.ia.exception.ErrorBundle;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class SeriesDataRepository implements SeriesRepository{

    private final ReadOnlyDataSource readOnlyDataSource;
    private final ReadWriteDataSource readWriteDataSource;
    private final Context context;

    @Inject
    SeriesDataRepository(ReadOnlyDataSource readOnlyDataSource, ReadWriteDataSource readWriteDataSource, @ForApp Context context) {
        this.readOnlyDataSource = readOnlyDataSource;
        this.readWriteDataSource = readWriteDataSource;
        this.context = context;
    }

    @Override
    public void getSeries(Integer page, GetSeriesCallback callback) {
        try {
            List<Series> seriesList;
            if (ConnectivityChecker.isNetworkAvailable(context)) {
                seriesList = readOnlyDataSource.getSeries(page);
                for (Series series : seriesList) {
                    if (readWriteDataSource.checkSeriesFavourite(series.getId())) {
                        series.setFavourite(true);
                    }
                }
                readWriteDataSource.writeSeries(seriesList);
            }
            else {
                seriesList = readWriteDataSource.getSeries(page);
            }
            callback.onSuccess(seriesList);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
}

    @Override
    public void getSeriesDetails(int seriesId, GetSeriesDetailsCallback callback) {
        try {
            Series series;
            if (ConnectivityChecker.isNetworkAvailable(context)) {
                series = readOnlyDataSource.getSeriesDetails(seriesId);
                if (readWriteDataSource.checkSeriesFavourite(series.getId())) {
                    series.setFavourite(true);
                }
                readWriteDataSource.writeSeriesDetails(series);
            }
            else {
                series = readWriteDataSource.getSeriesDetails(seriesId);
            }
            callback.onSuccess(series);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void makeSeriesFavourite(int seriesId, MakeSeriesFavouriteCallback callback) {
        try {
            readWriteDataSource.makeSeriesFavourite(seriesId);
            callback.onSuccess(null);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void removeSeriesFavourite(int seriesId, RemoveSeriesFavouriteCallback callback) {
        try {
            readWriteDataSource.removeSeriesFavourite(seriesId);
            callback.onSuccess(null);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }
}
