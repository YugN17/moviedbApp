package com.ia.moviedb.dependencyinjection.application;

import android.content.Context;

import com.ia.data.dependencyinjection.DataModule;
import com.ia.data.dependencyinjection.ForApp;
import com.ia.data.executor.JobExecutor;
import com.ia.executor.PostExecutionThread;
import com.ia.executor.ThreadExecutor;
import com.ia.moviedb.App;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.PicassoLoader;
import com.ia.moviedb.UIThread;
import com.ia.moviedb.moviedetail.ActorAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataModule.class})
public class ApplicationModule {


    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApp
    public Context providesContext(){
        return app;
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(PicassoLoader picassoLoader) {
        return picassoLoader;
    }
}
