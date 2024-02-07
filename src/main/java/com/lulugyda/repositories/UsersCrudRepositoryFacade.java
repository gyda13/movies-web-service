package com.lulugyda.repositories;

import com.lulugyda.exceptions.MovieException;
import com.lulugyda.exceptions.models.ErrorCode;
import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.lulugyda.exceptions.DatabaseExceptionHandler.handleDatabaseException;
import static com.lulugyda.utils.Constants.USER_NOT_FOUND;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UsersCrudRepositoryFacade {

    private final UsersCrudRepository usersCrudRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        try {
            log.info("saveUser:: saving new user");
            return  usersCrudRepository.save(userEntity);
        } catch (Exception exception) {
            log.error("saveUser:: Exception when saving a new user");
           handleDatabaseException(exception);
           return null;
        }
    }

    public Optional<UserEntity> findUser(Integer userId) {
        Optional<UserEntity> userEntity = Optional.empty();

        try {
            log.info("findUser for user id {}", userId);
            userEntity = usersCrudRepository.findById(userId);
            if(userEntity.isEmpty()){
                throw new MovieException(ErrorCode.INTERNAL_SERVER_ERROR.getId(), USER_NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("findUser:: Exception when finding user for id {}",
                    userId);
            handleDatabaseException(exception);
        }
        return userEntity;
    }

    public void updateUser(UserEntity user) {
        try {
            log.info("updateUser for user id {}", user.getId());
            usersCrudRepository.update(user);

        } catch (Exception exception) {
            log.error("updateUser:: Exception when updating user for id {}",
                    user.getId());
            handleDatabaseException(exception);
        }
    }

    @Transactional
    public void deleteUserMovie(Integer movieId, Integer userId) {
        Optional<UserEntity> userOptional = usersCrudRepository.findById(userId);
        try {
            log.info("deleteUserMovie for user id {}", userId);
            if(userOptional.isEmpty()){
                throw new MovieException(ErrorCode.INTERNAL_SERVER_ERROR.getId(), USER_NOT_FOUND);
            }
            UserEntity user = userOptional.get();
            List<MovieEntity> userMovies = user.getMovieEntity();

            Iterator<MovieEntity> iterator = userMovies.iterator();
            while (iterator.hasNext()) {
                MovieEntity movie = iterator.next();
                if (movie.getMovieId().equals(movieId)) {
                    iterator.remove();
                }
            }

        } catch (Exception exception) {
            log.error("deleteUserMovie:: Exception when trying to delete a movie for user id {}",
                    userId);
            handleDatabaseException(exception);
        }

    }

}
