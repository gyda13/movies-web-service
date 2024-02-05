package com.lulugyda.services;

import com.lulugyda.models.entities.PhoneNumberEntity;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.responses.MovieDetailsResponse;
import com.lulugyda.models.responses.MovieListResponse;

import java.util.ArrayList;

public interface MovieService {
    MovieListResponse getMovieList(String page);

    MovieDetailsResponse getMovieDetails(String movieId);

    void saveUserMovies(Integer userId);

    void registerUser (UserEntity userEntity);

    void addPhoneNumbers (ArrayList<String> numbers, Integer userّId);

}
