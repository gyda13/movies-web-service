package com.lulugyda.controllers;

import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserMoviesEntity;
import com.lulugyda.models.responses.MovieListResponse;
import com.lulugyda.services.MovieService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.lulugyda.utils.Constants.HEADER_X_CORRELATION_ID;
import static com.lulugyda.utils.Constants.USER_ID;
import static io.micronaut.http.MediaType.APPLICATION_JSON;
import com.lulugyda.models.responses.MovieDetailsResponse;
import io.micronaut.http.HttpStatus;

import java.util.List;


@Controller("v1/movies")
@Validated
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @Get(produces = APPLICATION_JSON)
    @Operation(description = "Discover Movies")
    @ApiResponse(responseCode = "200", description = "Success - Movie Details",
            content = @Content(schema = @Schema(implementation = MovieListResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ExecuteOn(TaskExecutors.IO)
    HttpResponse<MovieListResponse> getMovieList(
            @Positive(message = "Page number should be grater than 1")
            @QueryValue(defaultValue = "1") Integer page,
            @Header(HEADER_X_CORRELATION_ID) String correlationId) {
        MovieListResponse response = movieService.getMovieList(String.valueOf(page));
        return HttpResponse.ok(response);
    }

    @Get(value = "/{movieId}", produces = APPLICATION_JSON)
    @Operation(description = "Movie Details")
    @ApiResponse(responseCode = "200", description = "Success - Movie Details",
            content = @Content(schema = @Schema(implementation = MovieDetailsResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<MovieDetailsResponse> getMovieDetails(
            @PathVariable(value = "movieId")
            @Pattern(regexp = "[0-9]+", message = "MovieId should be number") String movieId,
            @Header(HEADER_X_CORRELATION_ID) String correlationId) {

        MovieDetailsResponse response = movieService.getMovieDetails(movieId);
        return HttpResponse.status(HttpStatus.valueOf(HttpStatus.OK.getCode())).body(response);
    }

    @Post(value = "/favourites", produces = APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<MovieEntity>> saveUserMovies(
            @Header(USER_ID) Integer userId,
            @Header(HEADER_X_CORRELATION_ID) String correlationId,
            @Body List<MovieEntity> movieEntity) {
        List<MovieEntity> movies = movieService.saveUserMovies(userId, movieEntity);
        return HttpResponse.ok(movies);
    }

}
