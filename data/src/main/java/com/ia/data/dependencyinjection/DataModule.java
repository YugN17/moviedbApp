/*
Created by Helm  1/2/17.
*/


package com.ia.data.dependencyinjection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ia.MovieRepository;
import com.ia.SeriesRepository;
import com.ia.data.repository.MovieDataRepository;
import com.ia.data.repository.SeriesDataRepository;
import com.ia.data.repository.datasource.Constants;
import com.ia.data.repository.datasource.ReadOnlyDataSource;
import com.ia.data.repository.datasource.ReadWriteDataSource;
import com.ia.data.repository.datasource.cloud.ApiDataSource;
import com.ia.data.repository.datasource.cloud.ApiDataSourceInterceptor;
import com.ia.data.repository.datasource.cloud.MovieService;
import com.ia.data.repository.datasource.cloud.SeriesService;
import com.ia.data.repository.datasource.local.RoomDataSource;
import com.ia.data.repository.datasource.local.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    public MovieRepository providesMoviesRepository(MovieDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    public SeriesRepository providesSeriesRepository(SeriesDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    public ReadOnlyDataSource providesReadOnlyDataSource(ApiDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public ReadWriteDataSource providesReadWriteDataSource (RoomDataSource roomDataSource) {
        return roomDataSource;
    }

    @Provides
    @Singleton
    public MovieService providesMovieService (Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    @Singleton
    public SeriesService providesSeriesService (Retrofit retrofit) {
        return retrofit.create(SeriesService.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit () {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ApiDataSourceInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }


    @Provides
    @Singleton
    public AppDatabase providesAppDatabase (@ForApp Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "movies").build();
    }
}
