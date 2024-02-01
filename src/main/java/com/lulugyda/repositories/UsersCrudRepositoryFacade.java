package com.lulugyda.repositories;

import com.lulugyda.models.entities.UserEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.lulugyda.exceptions.DatabaseExceptionHandler.handleDatabaseException;

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

    public Optional<UserEntity> findUser(String userId) {
        Optional<UserEntity> userEntity = Optional.empty();

        try {
            log.info("findUser for user id {}", userId);
            userEntity = usersCrudRepository.findById(userId);
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

}
