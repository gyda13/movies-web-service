package com.lulugyda.models.dtos;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class MovieEntityDto {

    private Integer movieId;
    private String movieTitle;

}
