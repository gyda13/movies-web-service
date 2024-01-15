package com.lulugyda.services;

import com.lulugyda.clients.TmdbClientFacade;
import com.lulugyda.mappers.TmdbMovieDetailsMapper;
import com.lulugyda.models.responses.MovieDetailsResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class MovieServiceFacade implements MovieService {

    private final TmdbClientFacade tmdbClientFacade;

    @Override
    public MovieDetailsResponse getMovieDetails(String movieId) {
        return TmdbMovieDetailsMapper.INSTANCE.mapToMovieDetailsResponse(
                tmdbClientFacade.getMovieDetails("", movieId));
    }
}
