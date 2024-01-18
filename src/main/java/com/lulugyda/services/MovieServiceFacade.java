package com.lulugyda.services;

import com.lulugyda.clients.TmdbClientFacade;
import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.clients.models.responses.TmdbMovieReviewersResponse;
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

        TmdbMovieReviewersResponse movieReviewers = tmdbClientFacade.getMovieReviewers(movieId);

        TmdbMovieDetailsResponse movieDetails = tmdbClientFacade.getMovieDetails(movieId);

        return buildMovieDetailsResponse(movieDetails, movieReviewers);

    }

    private MovieDetailsResponse buildMovieDetailsResponse(TmdbMovieDetailsResponse movieDetails,
                                                           TmdbMovieReviewersResponse movieReviewers) {
        return MovieDetailsResponse.builder()
                .id(movieDetails.getId())
                .budget(movieDetails.getBudget())
                .originalTitle(movieDetails.getOriginalTitle())
                .originalLanguage(movieDetails.getOriginalLanguage())
                .adult(movieDetails.getAdult())
                .voteAverage(movieDetails.getVoteAverage())
                .voteCount(movieDetails.getVoteCount())
                .releaseDate(movieDetails.getReleaseDate())
                .genres(TmdbMovieDetailsMapper.INSTANCE.mapToGenresResponse(movieDetails.getGenres()))
                .status(movieDetails.getStatus())
                .reviewers(TmdbMovieDetailsMapper.INSTANCE.maoToMovieReviewersResponse(movieReviewers)).build();

    }

}
