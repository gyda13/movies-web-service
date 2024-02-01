package com.lulugyda.repositories;

import com.lulugyda.models.entities.MovieEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface MoviesCrudRepository extends CrudRepository<MovieEntity, String> {
}
