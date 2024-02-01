package com.lulugyda.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
public class MovieEntity {

    @Id
    @Column(name = "movie_id", unique = true)
    @JsonProperty("movieId")
    private String movieId;

    @Column(name = "movie_title")
    @JsonProperty("movieTitle")
    private String movieTitle;


    @ManyToMany(mappedBy = "movieEntity")
    private List<UserEntity> users;

}
