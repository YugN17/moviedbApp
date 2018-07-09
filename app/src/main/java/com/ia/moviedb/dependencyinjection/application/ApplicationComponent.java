package com.ia.moviedb.dependencyinjection.application;

import com.ia.moviedb.App;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentComponent;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentModule;
import com.ia.moviedb.dependencyinjection.Fragment.FragmentViewModule;
import com.ia.moviedb.dependencyinjection.activity.ActivityComponent;
import com.ia.moviedb.dependencyinjection.activity.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

    ActivityComponent plus(ActivityModule activityModule, ViewModule viewModule);
    FragmentComponent plus(FragmentModule fragmentModule, FragmentViewModule fragmentViewModule);

}
