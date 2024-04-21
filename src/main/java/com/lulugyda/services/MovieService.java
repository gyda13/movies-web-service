package com.lulugyda.services;

import com.lulugyda.models.dtos.MovieEntityDto;
import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.responses.MovieListResponse;

import java.util.ArrayList;
import java.util.List;

public interface MovieService {
    MovieListResponse getMovieList(String page);

    String getMovieDetails(String movieId);

    List<MovieEntity> saveUserMovies(Integer userId, List<MovieEntity> movieEntity);

    void addPhoneNumbers (ArrayList<String> numbers, UserEntity user);

    List<MovieEntityDto> findUserMovies(Integer userId);

    void deleteUserMovie(Integer userId, Integer movieId);


}
