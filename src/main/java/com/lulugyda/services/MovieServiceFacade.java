package com.lulugyda.services;

import com.lulugyda.clients.TmdbClientFacade;
import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.clients.models.responses.TmdbMovieReviewersResponse;
import com.lulugyda.mappers.TmdbMovieDetailsMapper;
import com.lulugyda.models.dtos.MovieEntityDto;
import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.PhoneNumberEntity;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.models.responses.MovieListResponse;
import com.lulugyda.repositories.PhoneNumbersCrudRepositoryFacade;
import com.lulugyda.repositories.UsersCrudRepositoryFacade;
import com.lulugyda.repositories.*;
import com.lulugyda.security.BCryptPasswordEncoderService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class MovieServiceFacade implements MovieService {

    private final TmdbClientFacade tmdbClientFacade;
    private final UsersCrudRepositoryFacade usersCrudRepositoryFacade;
    private final PhoneNumbersCrudRepositoryFacade phoneNumbersCrudRepositoryFacade;
    private final MoviesCrudRepositoryFacade moviesCrudRepositoryFacade;


    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;

    @Override
    public MovieListResponse getMovieList(String page) {
        return  TmdbMovieDetailsMapper.INSTANCE.mapToMovieListResponse(tmdbClientFacade.getMovieList(page));
    }

    @Override
    public MovieDetailsResponse getMovieDetails(String movieId) {

        TmdbMovieDetailsResponse movieDetails = tmdbClientFacade.getMovieDetails(movieId);

        TmdbMovieListResponse similarMoviesResponse =  tmdbClientFacade.getSimilarMovies(movieId);

        TmdbMovieReviewersResponse movieReviewers = tmdbClientFacade.getMovieReviewers(movieId);


        MovieDetailsResponse movieDetailsResponse =
                TmdbMovieDetailsMapper.INSTANCE.mapToMovieDetailsResponse(movieDetails);

        movieDetailsResponse.setReviewers(
                TmdbMovieDetailsMapper.INSTANCE.maoToMovieReviewersResponse(movieReviewers));
        movieDetailsResponse.setSimilarMovies(
                TmdbMovieDetailsMapper.INSTANCE.mapToMovieListResponse(similarMoviesResponse).getResults());

        return movieDetailsResponse;
    }

    @Override
    public List<MovieEntity> saveUserMovies(Integer userId, List<MovieEntity> movieEntity) {
        return moviesCrudRepositoryFacade.saveUserMovies(userId, movieEntity);
    }

    @Override
    public void registerUser(UserEntity userEntity) {
       String encodedPassword = bCryptPasswordEncoderService.encode(userEntity.getPassword());
       userEntity.setPassword(encodedPassword);
        usersCrudRepositoryFacade.saveUser(userEntity);

    }

    @Override
    public void addPhoneNumbers(ArrayList<String> numbers, Integer userId) {
        Optional<UserEntity> user = usersCrudRepositoryFacade.findUser(userId);

        if (user.isPresent()) {
            for (String number : numbers) {
                phoneNumbersCrudRepositoryFacade.savePhoneNumber(
                        PhoneNumberEntity.builder()
                                .mobileNumber(number)
                                .user(user.get())
                                .build());
            }
        }
    }

    @Override
    public List<MovieEntityDto> findUserMovies(Integer userId) {
        List<MovieEntity> movies = moviesCrudRepositoryFacade.findUserMovies(userId);
        List<MovieEntityDto> movieEntityDtos = new ArrayList<>();

        for (MovieEntity movie : movies) {
            MovieEntityDto movieEntityDto = new MovieEntityDto();
            movieEntityDto.setMovieId(movie.getMovieId());
            movieEntityDto.setMovieTitle(movie.getMovieTitle());
            movieEntityDtos.add(movieEntityDto);
        }

        return movieEntityDtos;

    }

    @Override
    public void deleteUserMovie(Integer userId, Integer movieId) {
        usersCrudRepositoryFacade.deleteUserMovie(movieId, userId);
    }

}
