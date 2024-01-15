package com.lulugyda.services;

import com.lulugyda.clients.models.responses.TmdbMovieListResponse;

public interface MovieService {
    TmdbMovieListResponse getMovieList(String page);
}
