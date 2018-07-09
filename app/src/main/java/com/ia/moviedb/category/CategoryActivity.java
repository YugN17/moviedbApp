package com.ia.moviedb.category;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ia.moviedb.App;
import com.ia.moviedb.R;
import com.ia.moviedb.dependencyinjection.activity.ActivityComponent;
import com.ia.moviedb.dependencyinjection.activity.ActivityModule;
import com.ia.moviedb.dependencyinjection.application.ViewModule;
import com.ia.moviedb.dependencyinjection.qualifier.ForActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements CategoryView, FragmentInteractor {

    @Inject
    public CategoryPresenter categoryPresenter;

    @Inject
    @ForActivity
    Context context;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        activityComponent = ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this));
        activityComponent.inject(this);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));

        setUpAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        float alpha = (float) 1;
        viewPager.setAlpha(alpha);
    }

    private void setUpAdapter() {
        final CategoryAdapter categoryAdapter = new CategoryAdapter(getSupportFragmentManager());
        viewPager.setAdapter(categoryAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void OnItemSelected() {
        float alpha = (float) 0.45;
        viewPager.setAlpha(alpha);
    }

    @Override
    protected void onStop() {
        super.onStop();
        float alpha = (float) 1;
        viewPager.setAlpha(alpha);
    }
}
