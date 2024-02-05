package com.lulugyda.repositories;

import com.lulugyda.models.entities.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UsersCrudRepository extends CrudRepository<UserEntity, Integer> {

    @EntityGraph(attributePaths = {"movieEntity"})
    Optional<UserEntity> findById(Integer userId);

}
