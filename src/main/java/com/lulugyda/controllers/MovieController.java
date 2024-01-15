package com.lulugyda.controllers;

import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.services.MovieService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.micronaut.http.HttpHeaders.AUTHORIZATION;

@Controller("v1/movies")
@Slf4j
@Validated
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    @Get(value = "/{movieId}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<MovieDetailsResponse> getMovieDetails(@PathVariable(value = "movieId") String movieId) {
        MovieDetailsResponse response = movieService.getMovieDetails(movieId);
        return HttpResponse.status(HttpStatus.valueOf(HttpStatus.OK.getCode())).body(response);
    }
}
