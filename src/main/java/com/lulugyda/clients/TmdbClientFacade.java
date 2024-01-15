package com.lulugyda.clients;

import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import io.micronaut.context.annotation.Value;
import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
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

    public TmdbMovieDetailsResponse getMovieDetails(String movieId){
        try {
            HttpResponse<TmdbMovieDetailsResponse> response = tmdbClient.getMovieDetails(token, movieId);

            if (response.getBody().isEmpty()) {
            }

            return response.getBody().orElse(null);
        } catch (Exception exception) {
            throw exception;
        }
    }



    public TmdbMovieListResponse getMovieList(String page) {

       HttpResponse<TmdbMovieListResponse> response = tmdbClient.getMovieList(token,page);

        return response.body();
    }

}
