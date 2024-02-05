package com.lulugyda.services;

import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.models.responses.MovieListResponse;

import java.util.ArrayList;
import java.util.List;

public interface MovieService {
    MovieListResponse getMovieList(String page);

    MovieDetailsResponse getMovieDetails(String movieId);

    List<MovieEntity> saveUserMovies(Integer userId, List<MovieEntity> movieEntity);

    void registerUser (UserEntity userEntity);

    void addPhoneNumbers (ArrayList<String> numbers, Integer userّId);

}
