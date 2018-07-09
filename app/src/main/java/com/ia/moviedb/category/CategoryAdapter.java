package com.ia.moviedb.category;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ia.moviedb.movies.MoviesFragment;
import com.ia.moviedb.series.SeriesFragment;

public class CategoryAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Movies", "Series"};

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MoviesFragment();
            case 1:
                return new SeriesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
