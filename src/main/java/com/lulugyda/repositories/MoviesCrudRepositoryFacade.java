package com.lulugyda.repositories;

import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
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

    public void saveUserMovies(Integer userId) {
        try {
            log.info("saveUserMovies for user id {}", userId);

            MovieEntity m1 = new MovieEntity(1, "t1", null);
            MovieEntity m2 = new MovieEntity(2, "gydaamovie", null);
            List<MovieEntity> movies = Arrays.asList(m1, m2);

            List<MovieEntity> saveMovies = movies.stream()
                    .filter(fav -> !moviesCrudRepository.findById(fav.getMovieId()).isPresent())
                    .collect(Collectors.toList());

            moviesCrudRepository.saveAll(saveMovies);


            Optional<UserEntity> user = usersCrudRepositoryFacade.findUser(userId);
            user.get().setMovieEntity(movies);
            usersCrudRepositoryFacade.updateUser(user.get());

        } catch (Exception exception) {
            log.error("saveUserMovies:: Exception when saving movies for user id {}", userId);
            handleDatabaseException(exception);
        }
    }

}