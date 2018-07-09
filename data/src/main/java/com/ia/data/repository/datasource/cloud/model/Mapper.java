package com.ia.data.repository.datasource.cloud.model;


import com.ia.data.repository.datasource.cloud.model.movies.MovieDTO;
import com.ia.data.repository.datasource.cloud.model.series.SerieDTO;
import com.ia.data.repository.datasource.cloud.model.series.SeriesEpisodeDTO;
import com.ia.data.repository.datasource.cloud.model.series.SeriesSeasonDTO;
import com.ia.entities.Actor;
import com.ia.entities.Genre;
import com.ia.entities.movies.Movie;
import com.ia.entities.series.Series;
import com.ia.entities.series.SeriesEpisode;
import com.ia.entities.series.SeriesSeason;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static Movie map(MovieDTO body) {
        List<Genre> genreList = new ArrayList<>();
        if (body.getGenres() != null) {
            for (int i = 0; i < body.getGenres().size(); i++) {
                GenreDTO genreDTO = body.getGenres().get(i);
                genreList.add(new Genre(genreDTO.getId(), genreDTO.getName()));
            }
        }
        return new Movie(body.getId(), body.getTitle(), body.getOverview(), body.getPosterImage(), body.getBackgroundImage(), body.getRating(), genreList, false);
    }

    public static Series map(SerieDTO body) {
        List<Genre> genreList = new ArrayList<>();
        if (body.getGenres() != null) {
            for (int i = 0; i < body.getGenres().size(); i++) {
                GenreDTO genreDTO = body.getGenres().get(i);
                genreList.add(new Genre(genreDTO.getId(), genreDTO.getName()));
            }
        }

        List<SeriesSeason> seriesSeasonList = new ArrayList<>();
        if (body.getSeasons() != null) {
            for (int i = 0; i < body.getSeasons().size(); i++) {
                SeriesSeasonDTO seriesSeasonDTO = body.getSeasons().get(i);
                seriesSeasonList.add(new SeriesSeason(seriesSeasonDTO.getId(), seriesSeasonDTO.getSeasonNumber(), seriesSeasonDTO.getEpisodeCount()));
            }
        }

        return new Series(body.getId(), body.getTitle(), body.getOverview(), body.getPosterImage(), body.getBackgroundImage(), body.getRating(), genreList, seriesSeasonList, false);
    }

    public static SeriesSeason map(SeriesSeasonDTO body) {
        List<SeriesEpisode> seriesEpisodeList = new ArrayList<>();
        for (int i = 0; i < body.getEpisodes().size(); i++) {
            SeriesEpisodeDTO seriesEpisodeDTO = body.getEpisodes().get(i);
            seriesEpisodeList.add(map(seriesEpisodeDTO));
        }
        return new SeriesSeason(body.getId(), body.getSeasonNumber(), body.getEpisodes().size(), seriesEpisodeList);
    }

    public static SeriesEpisode map(SeriesEpisodeDTO body) {
        return new SeriesEpisode(body.getId(), body.getEpisodeNumber(), body.getName());
    }

    public static List<Movie> map(List<MovieDTO> results) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            movieList.add(Mapper.map(results.get(i)));
        }
        return movieList;
    }

    public static List<String> mapImages(List<ImageDTO> results) {
        List<String> movieImageList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            String image = results.get(i).getImage();
            movieImageList.add(image);
        }
        return movieImageList;
    }

    public static List<Actor> mapActors(List<ActorDTO> results) {
        List<Actor> actorList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Actor actor = new Actor(results.get(i).getId(),
                    results.get(i).getCharacter(),
                    results.get(i).getName(),
                    results.get(i).getImage());
            actorList.add(actor);
        }
        return actorList;
    }

    public static List<Series> mapSeries(List<SerieDTO> results) {
        List<Series> seriesList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            seriesList.add(Mapper.map(results.get(i)));
        }
        return seriesList;
    }
}
