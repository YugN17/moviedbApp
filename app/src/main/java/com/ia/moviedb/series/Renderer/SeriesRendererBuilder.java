package com.ia.moviedb.series.Renderer;


import com.ia.entities.movies.Movie;
import com.ia.moviedb.ImageLoader;
import com.ia.moviedb.series.SeriesPresenter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.LinkedList;
import java.util.List;

public class SeriesRendererBuilder extends RendererBuilder<Movie> {

    private ImageLoader imageLoader;
    private SeriesPresenter presenter;

    public SeriesRendererBuilder (ImageLoader imageLoader, SeriesPresenter presenter) {
        this.imageLoader = imageLoader;
        this.presenter = presenter;
        List<Renderer<Movie>> prototypes = getRendererSeriesPrototypes();
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(Movie content) {
        Class prototypeClass;

        if(content.getFavourite()) prototypeClass = FavouriteSeriesRenderer.class;
        else prototypeClass = SeriesRenderer.class;

        return prototypeClass;
    }

    private List<Renderer<Movie>> getRendererSeriesPrototypes() {
        List<Renderer<Movie>> prototypes = new LinkedList<>();

        prototypes.add(new SeriesRenderer(imageLoader, presenter));
        prototypes.add(new FavouriteSeriesRenderer(imageLoader, presenter));

        return prototypes;
    }

}
