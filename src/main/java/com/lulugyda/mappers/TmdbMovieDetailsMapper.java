package com.lulugyda.mappers;

import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.clients.models.responses.TmdbResultsResponse;
import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.models.responses.MovieListResponse;
import com.lulugyda.models.responses.ResultsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TmdbMovieDetailsMapper {
    TmdbMovieDetailsMapper INSTANCE = Mappers.getMapper(TmdbMovieDetailsMapper.class);

    MovieDetailsResponse mapToMovieDetailsResponse(TmdbMovieDetailsResponse movieDetailsResponse);


    MovieListResponse mapToMovieListResponse(TmdbMovieListResponse tmdbMovieListResponse);

    @Mapping(target = "isAboveAverage", expression =
            "java(calc(tmdbResultsResponse))")
    ResultsResponse mapToResultsResponse(TmdbResultsResponse tmdbResultsResponse);

    default boolean calc(TmdbResultsResponse tmdbResultsResponse) {
        return Double.parseDouble(tmdbResultsResponse.getVoteAverage()) > 5;
    }

}
