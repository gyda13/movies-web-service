package com.lulugyda.controllers;

import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.services.MovieService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static io.micronaut.http.MediaType.APPLICATION_JSON;
import com.lulugyda.models.responses.MovieDetailsResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.validation.Validated;

@Controller("v1/movies")
@Slf4j
@Validated
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @Get(produces = APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    HttpResponse<TmdbMovieListResponse> getMovieList(@QueryValue String page) {

        return HttpResponse.ok(movieService.getMovieList(page));
    }
    @Get(value = "/{movieId}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<MovieDetailsResponse> getMovieDetails(@PathVariable(value = "movieId") String movieId) {
        MovieDetailsResponse response = movieService.getMovieDetails(movieId);
        return HttpResponse.status(HttpStatus.valueOf(HttpStatus.OK.getCode())).body(response);
    }
}
