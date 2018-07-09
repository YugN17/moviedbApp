package com.ia.data.repository.datasource.cloud;

import com.ia.data.repository.datasource.cloud.model.ActorCollectionDTO;
import com.ia.data.repository.datasource.cloud.model.ImageCollectionDTO;
import com.ia.data.repository.datasource.cloud.model.movies.MovieDTO;
import com.ia.data.repository.datasource.cloud.model.movies.MoviesDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("discover/movie")
    Call<MoviesDTO> discoverMovies(@Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieDTO> getMovieDetails(@Path("movie_id") int id);

    @GET("movie/{movie_id}/images")
    Call<ImageCollectionDTO> getMovieImages(@Path("movie_id") int id);

    @GET("movie/{movie_id}/credits")
    Call<ActorCollectionDTO> getMovieCharacters(@Path("movie_id") int id);

}
