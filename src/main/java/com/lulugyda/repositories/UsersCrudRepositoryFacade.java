package com.lulugyda.repositories;

import com.lulugyda.exceptions.InvalidUsernameOrPasswordException;
import com.lulugyda.exceptions.UserNotFoundException;
import com.lulugyda.models.entities.MovieEntity;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.security.BCryptPasswordEncoderService;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static com.lulugyda.exceptions.ExceptionManager.handleException;


@Slf4j
@Singleton
@RequiredArgsConstructor
public class UsersCrudRepositoryFacade {

    private final UsersCrudRepository usersCrudRepository;

    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;

    public UserEntity saveUser(UserEntity userEntity) {
        try {
            log.info("saveUser:: saving new user");
            return  usersCrudRepository.save(userEntity);
        } catch (Exception exception) {
            log.error("saveUser:: Exception when saving a new user");
           handleException(exception);
           return null;
        }
    }

    public Optional<UserEntity> findUser(Integer userId) {
        Optional<UserEntity> userEntity = Optional.empty();

        try {
            log.info("findUser for user id {}", userId);
            userEntity = usersCrudRepository.findById(userId);
            if(userEntity.isEmpty()) {
                throw new UserNotFoundException();
            }
        } catch (Exception exception) {
            log.error("findUser:: Exception when finding user for id {}",
                    userId);
            handleException(exception);
        }
        return userEntity;
    }

    public void updateUser(UserEntity user) {
        try {
            log.info("updateUser for user id {}", user.getId());
            UserEntity userEntity = usersCrudRepository.update(user);
            if(userEntity == null) {
                throw new UserNotFoundException();
            }

        } catch (Exception exception) {
            log.error("updateUser:: Exception when updating user for id {}",
                    user.getId());
            handleException(exception);
        }
    }

    @Transactional
    public void deleteUserMovie(Integer movieId, Integer userId) {
        Optional<UserEntity> userOptional = usersCrudRepository.findById(userId);
        try {
            log.info("deleteUserMovie for user id {}", userId);
            if(userOptional.isEmpty()) {
                throw new UserNotFoundException();
            }
            UserEntity user = userOptional.get();
            List<MovieEntity> userMovies = user.getMovieEntity();

            userMovies.removeIf(movie -> movie.getMovieId().equals(movieId));

        } catch (Exception exception) {
            log.error("deleteUserMovie:: Exception when trying to delete a movie for user id {}",
                    userId);
            handleException(exception);
        }

    }

    public boolean validCredentials(String username, String password) {

        Optional<UserEntity> user = usersCrudRepository.findByUsername(username);
        try {
            if (user.isEmpty()) {
                throw new InvalidUsernameOrPasswordException();
            }
        } catch (Exception exception) {
            handleException(exception);

        }
        return bCryptPasswordEncoderService.matches(password, user.get().getPassword());
    }

    public UserEntity getUserIdByUsername(String username) {

        Optional<UserEntity> user = usersCrudRepository.findByUsername(username);
        try {
            if (user.isEmpty()) {
                throw new UserNotFoundException();
            }

        } catch (Exception exception) {
            handleException(exception);

        }
        return user.orElse(null);

    }
}
