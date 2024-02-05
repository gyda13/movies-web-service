package com.lulugyda.repositories;

import com.lulugyda.models.entities.MovieEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface MoviesCrudRepository extends CrudRepository<MovieEntity, Integer> {

    @EntityGraph(attributePaths = {"users"})
    Optional<MovieEntity> findByMovieId(Integer movieId);

}
