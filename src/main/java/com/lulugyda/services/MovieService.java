package com.lulugyda.services;


import com.lulugyda.models.responses.MovieDetailsResponse;

public interface MovieService {

    MovieDetailsResponse getMovieDetails(String movieId);
}
