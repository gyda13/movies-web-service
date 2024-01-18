package com.lulugyda.controllers;

import com.lulugyda.models.responses.MovieListResponse;
import com.lulugyda.services.MovieService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    HttpResponse<MovieListResponse> getMovieList(@QueryValue String page) {
        return HttpResponse.ok(movieService.getMovieList(page));
    }

    @Get(value = "/{movieId}")
    @Operation(description = "Movie Details")
    @ApiResponse(responseCode = "200", description = "Success - Movie Details",
            content = @Content(schema = @Schema(implementation = MovieDetailsResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<MovieDetailsResponse> getMovieDetails(@PathVariable(value = "movieId") String movieId) {
        MovieDetailsResponse response = movieService.getMovieDetails(movieId);
        return HttpResponse.status(HttpStatus.valueOf(HttpStatus.OK.getCode())).body(response);
    }
}
