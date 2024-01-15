package com.lulugyda.controllers;


import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.services.MovieService;
import com.lulugyda.services.MovieServiceFacade;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/v1/movies")
@Slf4j
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @Get(produces = APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    HttpResponse<TmdbMovieListResponse> getMovieList(@QueryValue String page) {

        return HttpResponse.ok(movieService.getMovieList(page));
    }
}
