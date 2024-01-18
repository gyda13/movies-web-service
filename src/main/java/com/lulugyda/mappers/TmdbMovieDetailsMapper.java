package com.lulugyda.mappers;

import com.lulugyda.clients.models.responses.*;
import com.lulugyda.models.responses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface TmdbMovieDetailsMapper {
    TmdbMovieDetailsMapper INSTANCE = Mappers.getMapper(TmdbMovieDetailsMapper.class);

    MovieListResponse mapToMovieListResponse(TmdbMovieListResponse tmdbMovieListResponse);

    @Mapping(target = "isAboveAverage", expression =
            "java(calc(tmdbResultsResponse))")
    ResultsResponse mapToResultsResponse(TmdbResultsResponse tmdbResultsResponse);

    List<GenresResponse> mapToGenresResponse(List<TmdbGenresResponse> tmdbGenresResponse);

    @Mapping(target = "reviewersResult", source = "results")
    MovieReviewersResponse maoToMovieReviewersResponse(
            TmdbMovieReviewersResponse tmdbMovieReviewersResponse);

    @Mapping(target = "movieReviewerDetailsResponse", source = "tmdbMovieReviewerDetailsResponse")
    ReviewersResultListResponse mapToReviewersResultListResponse(
            TmdbReviewersResultListResponse tmdbReviewersResultListResponse);

    default boolean calc(TmdbResultsResponse tmdbResultsResponse) {
        return Double.parseDouble(tmdbResultsResponse.getVoteAverage()) > 5;
    }

}
