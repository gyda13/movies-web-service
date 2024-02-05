package com.lulugyda.repositories;

import com.lulugyda.exceptions.GenericExceptionHandler;
import com.lulugyda.exceptions.MovieException;
import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.lulugyda.exceptions.DatabaseExceptionHandler.handleDatabaseException;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class MoviesCrudRepositoryFacade {

    private final MoviesCrudRepository moviesCrudRepository;

    private final UsersCrudRepositoryFacade usersCrudRepositoryFacade;

    public List<MovieEntity> saveUserMovies(Integer userId, List<MovieEntity> movieEntity) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        try {
            log.info("saveUserMovies for user id {}", userId);

            Optional<UserEntity> user = usersCrudRepositoryFacade.findUser(userId);

            movieEntityList = movieEntity.stream()
                    .filter(fav -> !moviesCrudRepository.findById(fav.getMovieId()).isPresent())
                    .collect(Collectors.toList());

            movieEntityList = moviesCrudRepository.saveAll(movieEntityList);

            user.get().setMovieEntity(movieEntity);
            usersCrudRepositoryFacade.updateUser(user.get());
            return movieEntityList;

        } catch (Exception exception) {
            log.error("saveUserMovies:: Exception when saving movies for user id {}", userId);
            handleDatabaseException(exception);
        }
        return null;
    }

}