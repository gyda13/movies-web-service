package com.lulugyda.clients;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import static io.micronaut.http.HttpHeaders.AUTHORIZATION;
import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Client(value = "${tmdb-client.api.url}")
public interface TmdbClient {

    @Get(value = "/discover/movie", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    void getMovieList(@Header(value = AUTHORIZATION) String authorization,
                      @QueryValue(value = "page") String page);

}