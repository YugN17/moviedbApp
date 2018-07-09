package com.ia.moviedb.dependencyinjection.activity;

import android.app.Activity;
import android.content.Context;

import com.ia.moviedb.dependencyinjection.qualifier.ForActivity;
import com.ia.moviedb.dependencyinjection.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }


    @Provides
    @PerActivity
    @ForActivity
    Context providesContext(){
        return activity;
    }

}
