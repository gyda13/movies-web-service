package com.lulugyda.clients;

import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class TmdbClientFacade {

    private final TmdbClient tmdbClient;

    @Value("${tmdb.token}")
    private String token;

    public TmdbMovieListResponse getMovieList(String page) {

       HttpResponse<TmdbMovieListResponse> response = tmdbClient.getMovieList(token,page);

        return response.body();
    }
}
