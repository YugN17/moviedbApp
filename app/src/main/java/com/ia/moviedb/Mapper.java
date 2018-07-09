package com.ia.moviedb;


import android.util.Pair;

import com.ia.entities.Actor;
import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;
import com.ia.entities.series.SeriesEpisode;
import com.ia.entities.series.SeriesSeason;
import com.ia.moviedb.model.MovieViewEntity;
import com.ia.moviedb.model.SeasonViewEntity;
import com.ia.moviedb.model.SeriesViewEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Mapper {
    public static MovieViewEntity map(Movie returnParam) {
        MovieViewEntity movieViewEntity = new MovieViewEntity();
        movieViewEntity.setTitle(returnParam.getTitle());
        movieViewEntity.setOverview(returnParam.getOverview());
        movieViewEntity.setPosterImage(returnParam.getPosterImage());
        movieViewEntity.setBackgroundImage(returnParam.getBackgroundImage());
        movieViewEntity.setRating(returnParam.getRating());
        movieViewEntity.setGenres(returnParam.getGenres());

        List<String> movieImages = new ArrayList<>();
        List<String> movieImageList = returnParam.getMovieImageList();
        for (int i = 1; i < movieImageList.size() && i < 11; i++) {
            movieImages.add(movieImageList.get(i));
        }
        movieViewEntity.setImages(movieImages);

        List<Actor> actorList = new ArrayList<>();
        List<Actor> longActorList = returnParam.getActorList();
        for (int i = 0; i < longActorList.size() && i < 10; i++) {
            actorList.add(longActorList.get(i));

        }
        movieViewEntity.setActorList(actorList);


        return movieViewEntity;
    }

    public static SeriesViewEntity map(Series returnParam) {
        SeriesViewEntity seriesViewEntity = new SeriesViewEntity();
        seriesViewEntity.setTitle(returnParam.getTitle());
        seriesViewEntity.setOverview(returnParam.getOverview());
        seriesViewEntity.setPosterImage(returnParam.getPosterImage());
        seriesViewEntity.setBackgroundImage(returnParam.getBackgroundImage());
        seriesViewEntity.setRating(returnParam.getRating());
        seriesViewEntity.setGenres(returnParam.getGenres());

        List<String> seriesImages = new ArrayList<>();
        List<String> seriesImageList = returnParam.getSeriesImageList();
        for (int i = 1; i < seriesImageList.size() && i < 11; i++) {
            seriesImages.add(seriesImageList.get(i));
        }
        seriesViewEntity.setImages(seriesImages);

        List<Actor> actorList = new ArrayList<>();
        List<Actor> longActorList = returnParam.getActorList();
        for (int i = 0; i < longActorList.size() && i < 10; i++) {
            actorList.add(longActorList.get(i));

        }
        seriesViewEntity.setActorList(actorList);

        seriesViewEntity.setId(returnParam.getId());

        /**
         * Seasons
         */

        Map<Integer, SeasonViewEntity> seasons = new TreeMap<>();

        if ( returnParam.getSeasons() != null) {

            for ( SeriesSeason s : returnParam.getSeasons()){
                SeasonViewEntity season = new SeasonViewEntity();
                Map<Integer, String> episodes = new TreeMap<>();

                if (s.getEpisodes() != null) {
                    for (SeriesEpisode e : s.getEpisodes()) {
                        episodes.put(e.getEpisodeNumber(), e.getName());
                    }
                }

                season.setEpisodes(episodes);
                seasons.put(s.getSeasonNumber(), season);
            }
        }

        seriesViewEntity.setSeasons(seasons);

        return seriesViewEntity;
    }
}
