package com.lulugyda.repositories;

import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
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
                    .filter(fav -> !moviesCrudRepository.findByMovieId(fav.getMovieId()).isPresent())
                    .collect(Collectors.toList());

            moviesCrudRepository.saveAll(movieEntityList);


            List<MovieEntity> newMoviesList = user.get().getMovieEntity();
            newMoviesList.addAll(movieEntityList);
            user.get().setMovieEntity(newMoviesList);
            usersCrudRepositoryFacade.updateUser(user.get());
            return movieEntityList;

        } catch (Exception exception) {
            log.error("saveUserMovies:: Exception when saving movies for user id {}", userId);
            handleDatabaseException(exception);
        }
        return null;
    }

    public List<MovieEntity> findUserMovies(Integer userId) {
        try {
            log.info("findUserMovies for user id {}", userId);
            return moviesCrudRepository.findByUsersId(userId);
        } catch (Exception exception) {
            log.error("findUserMovies:: Exception when retrieving users movies for user id {}", userId);
            handleDatabaseException(exception);

        }
        return null;
    }
}