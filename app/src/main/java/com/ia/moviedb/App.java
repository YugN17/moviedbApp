package com.ia.moviedb;

import android.app.Application;

import com.ia.moviedb.dependencyinjection.application.ApplicationComponent;
import com.ia.moviedb.dependencyinjection.application.ApplicationModule;
import com.ia.moviedb.dependencyinjection.application.DaggerApplicationComponent;

public class App extends Application {

    ApplicationComponent component = null;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }
}
