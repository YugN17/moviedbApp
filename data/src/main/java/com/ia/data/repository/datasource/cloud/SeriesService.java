package com.ia.data.repository.datasource.cloud;

import com.ia.data.repository.datasource.cloud.model.ActorCollectionDTO;
import com.ia.data.repository.datasource.cloud.model.ImageCollectionDTO;
import com.ia.data.repository.datasource.cloud.model.series.SerieDTO;
import com.ia.data.repository.datasource.cloud.model.series.SeriesDTO;
import com.ia.data.repository.datasource.cloud.model.series.SeriesSeasonDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SeriesService {
    @GET("discover/tv")
    Call<SeriesDTO> discoverSeries(@Query("page") int page);

    @GET("tv/{tv_id}")
    Call<SerieDTO> getSeriesDetails(@Path("tv_id") int id);

    @GET("tv/{tv_id}/season/{season_number}")
    Call<SeriesSeasonDTO> getSeriesSeasonDetails(@Path("tv_id") int id, @Path("season_number") int seasonNumber);

    @GET("tv/{tv_id}/images")
    Call<ImageCollectionDTO> getSeriesImages(@Path("tv_id") int id);

    @GET("tv/{tv_id}/credits")
    Call<ActorCollectionDTO> getSeriesCharacters(@Path("tv_id") int id);
}
