package com.lulugyda.clients;

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

    public TmdbMovieDetailsResponse getMovieDetails(String authorization, String movieId){
        try {
            HttpResponse<TmdbMovieDetailsResponse> response = tmdbClient.getMovieDetails(authorization, movieId);

            if (response.getBody().isEmpty()) {
            }

            return response.getBody().orElse(null);
        } catch (Exception exception) {
            throw exception;
        }
    }


}
