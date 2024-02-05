package com.lulugyda.repositories;

import com.lulugyda.models.entities.UserMoviesEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UsersMoviesCrudRepository extends CrudRepository<UserMoviesEntity, Integer> {
}
