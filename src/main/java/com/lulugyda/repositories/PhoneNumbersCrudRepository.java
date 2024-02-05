package com.lulugyda.repositories;

import com.lulugyda.models.entities.PhoneNumberEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PhoneNumbersCrudRepository  extends CrudRepository<PhoneNumberEntity, Integer> {
}
