package com.ia.moviedb.movies.Renderer;

import com.ia.entities.movies.Movie;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.movies.MoviesPresenter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.LinkedList;
import java.util.List;

public class MovieRendererBuilder extends RendererBuilder<Movie> {

    private ImageLoader imageLoader;
    private MoviesPresenter presenter;

    public MovieRendererBuilder(ImageLoader imageLoader, MoviesPresenter presenter) {
        this.imageLoader = imageLoader;
        this.presenter = presenter;
        List<Renderer<Movie>> prototypes = getRendererMoviePrototypes();
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(Movie content) {
        Class prototypeClass;

        if(content.getFavourite()) prototypeClass = FavouriteMovieRenderer.class;
        else prototypeClass = MovieRenderer.class;

        return prototypeClass;
    }

    private List<Renderer<Movie>> getRendererMoviePrototypes() {
        List<Renderer<Movie>> prototypes = new LinkedList<>();

        prototypes.add(new MovieRenderer(imageLoader, presenter));
        prototypes.add(new FavouriteMovieRenderer(imageLoader, presenter));

        return prototypes;
    }
}

