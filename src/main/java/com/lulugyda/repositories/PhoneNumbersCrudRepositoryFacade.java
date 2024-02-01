package com.lulugyda.repositories;

import com.lulugyda.models.entities.PhoneNumberEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.lulugyda.exceptions.DatabaseExceptionHandler.handleDatabaseException;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class PhoneNumbersCrudRepositoryFacade {

    private final PhoneNumbersCrudRepository phoneNumbersCrudRepository;

    public void savePhoneNumber(PhoneNumberEntity phoneNumberEntity) {
        try {
            log.info("savePhoneNumber:: for user id {}", phoneNumberEntity.getUser().getId());
            phoneNumbersCrudRepository.save(phoneNumberEntity);
        } catch (Exception exception) {
            log.error("savePhoneNumber:: Exception when saving user for id {}",
                    phoneNumberEntity.getId());
            handleDatabaseException(exception);
        }
    }
}