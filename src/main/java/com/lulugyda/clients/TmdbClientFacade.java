package com.lulugyda.clients;

import com.lulugyda.exceptions.MovieException;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.clients.models.responses.TmdbMovieReviewersResponse;
import com.lulugyda.exceptions.models.ErrorCode;
import io.micronaut.context.annotation.Value;
import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.lulugyda.exceptions.TmdbException.handleTmdbException;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class TmdbClientFacade {

    private final TmdbClient tmdbClient;


    @Value("${tmdb.token}")
    private String token;

    public TmdbMovieDetailsResponse getMovieDetails(String movieId) {
        HttpResponse<TmdbMovieDetailsResponse> response;

        try {
            response = tmdbClient.getMovieDetails(token, movieId);

            if (response.getBody().isEmpty()) {
                throw new MovieException(ErrorCode.EMPTY_BODY_RECEIVED.getId(),
                        ErrorCode.EMPTY_BODY_RECEIVED.getMessage());
            }

            return response.getBody().get();
        } catch (Exception exception) {
            handleTmdbException(exception);
        }
        throw new RuntimeException();
    }


    public TmdbMovieListResponse getMovieList(String page) {
        HttpResponse<TmdbMovieListResponse> response;
        try {
            response = tmdbClient.getMovieList(token, page);

            if (response.getBody().isEmpty()) {
                throw new MovieException(ErrorCode.EMPTY_BODY_RECEIVED.getId(),
                        ErrorCode.EMPTY_BODY_RECEIVED.getMessage());
            }

            return response.body();
        } catch (Exception exception) {
            handleTmdbException(exception);
        }
        throw new RuntimeException();
    }

    public TmdbMovieListResponse getSimilarMovies(String movieId) {

        HttpResponse<TmdbMovieListResponse> response;
        try {
            response = tmdbClient.getSimilarMovies(token, movieId);

            if (response.getBody().isEmpty()) {
                throw new MovieException(ErrorCode.EMPTY_BODY_RECEIVED.getId(),
                        ErrorCode.EMPTY_BODY_RECEIVED.getMessage());
            }

            return response.body();
        } catch (Exception exception) {
            handleTmdbException(exception);
        }

        throw new RuntimeException();
    }

    public TmdbMovieReviewersResponse getMovieReviewers(String movieId) {
        HttpResponse<TmdbMovieReviewersResponse> response;

        try {
            response = tmdbClient.getMovieReviewers(token, movieId);

            if (response.getBody().isEmpty()) {
                throw new MovieException(ErrorCode.EMPTY_BODY_RECEIVED.getId(),
                        ErrorCode.EMPTY_BODY_RECEIVED.getMessage());
            }

            return response.body();
        } catch (Exception exception) {
            handleTmdbException(exception);
        }

        throw new RuntimeException();
    }
}
