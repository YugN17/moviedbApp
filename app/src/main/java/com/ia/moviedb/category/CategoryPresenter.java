package com.ia.moviedb.category;


import javax.inject.Inject;

public class CategoryPresenter {
    private final CategoryView view;

    @Inject
    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }
}
