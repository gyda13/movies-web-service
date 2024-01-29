package com.lulugyda.repositories;

import com.lulugyda.models.entities.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UsersCrudRepository extends CrudRepository<UserEntity, String> {
}
