package com.lulugyda.models.entities;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Table(name = "Users_Movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class UserMoviesEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "id", unique = true, nullable = false)
//    private String id;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "movie_id")
    private Integer movieId;

}
