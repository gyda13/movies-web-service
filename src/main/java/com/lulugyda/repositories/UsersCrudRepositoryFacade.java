package com.lulugyda.repositories;

import com.lulugyda.models.entities.UserEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.lulugyda.exceptions.DatabaseExceptionHandler.handleDatabaseException;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UsersCrudRepositoryFacade {

    private final UsersCrudRepository usersCrudRepository;

    public void saveUser(UserEntity userEntity) {
        try {
            log.info("saveUser for user id {}", userEntity.getId());
            usersCrudRepository.save(userEntity);
        } catch (Exception exception) {
            log.error("saveUser:: Exception when saving user for id {}",
                    userEntity.getId());
           handleDatabaseException(exception);
        }
    }
}
