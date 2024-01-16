package com.lulugyda.services;

import com.lulugyda.clients.TmdbClientFacade;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.mappers.TmdbMovieDetailsMapper;
import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.models.responses.MovieListResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class MovieServiceFacade implements MovieService {

    private final TmdbClientFacade tmdbClientFacade;

    @Override
    public MovieListResponse getMovieList(String page) {
        return  TmdbMovieDetailsMapper.INSTANCE.mapToMovieListResponse(tmdbClientFacade.getMovieList(page));
    }
    @Override
    public MovieDetailsResponse getMovieDetails(String movieId) {
        TmdbMovieListResponse similarMoviesResponse =  tmdbClientFacade.getSimilarMovies(movieId);
        System.out.println(similarMoviesResponse);

        return TmdbMovieDetailsMapper.INSTANCE.mapToMovieDetailsResponse(
                tmdbClientFacade.getMovieDetails(movieId));

    }
}
