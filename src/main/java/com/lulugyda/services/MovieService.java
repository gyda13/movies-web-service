package com.lulugyda.services;

import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.models.responses.MovieListResponse;

public interface MovieService {
    MovieListResponse getMovieList(String page);

    MovieDetailsResponse getMovieDetails(String movieId);

}
