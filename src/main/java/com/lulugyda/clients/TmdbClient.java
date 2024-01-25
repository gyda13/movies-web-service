package com.lulugyda.clients;


import com.lulugyda.clients.models.responses.TmdbErrorResponse;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import com.lulugyda.clients.models.responses.TmdbMovieReviewersResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import static io.micronaut.http.HttpHeaders.AUTHORIZATION;
import static io.micronaut.http.MediaType.APPLICATION_JSON;
@Client(value = "${tmdb-client.api.url}", errorType = TmdbErrorResponse.class)
public interface TmdbClient {

    @Get(value = "/discover/movie", produces = APPLICATION_JSON)
    HttpResponse<TmdbMovieListResponse> getMovieList(@Header(value = AUTHORIZATION) String authorization,
                                                     @QueryValue(value = "page") String page);

    @Get(value = "/movie/{movie_id}/similar", produces = APPLICATION_JSON)
    HttpResponse<TmdbMovieListResponse> getSimilarMovies(@Header(value = AUTHORIZATION) String authorization,
                                                         @PathVariable(value = "movie_id") String movieId);

    @Get(value = "/movie/{movie_id}", produces = APPLICATION_JSON)
    HttpResponse<TmdbMovieDetailsResponse> getMovieDetails(
            @Header(value = AUTHORIZATION) String authorization,
            @PathVariable(value = "movie_id") String movieId);

    @Get(value = "/movie/{movie_id}/reviews", produces = APPLICATION_JSON)
    HttpResponse<TmdbMovieReviewersResponse> getMovieReviewers(
            @Header(value = AUTHORIZATION) String authorization,
            @PathVariable(value = "movie_id") String movieId);

}