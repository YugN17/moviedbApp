package com.ia.interactor.series.input;


public class SeasonInfo {

    private int seriesId;
    private int seasonNumber;

    public SeasonInfo(int seriesId, int seasonNumber) {
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }
}
