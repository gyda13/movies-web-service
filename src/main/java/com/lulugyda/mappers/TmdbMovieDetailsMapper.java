package com.lulugyda.mappers;

import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import com.lulugyda.models.responses.MovieDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TmdbMovieDetailsMapper {
    TmdbMovieDetailsMapper INSTANCE = Mappers.getMapper(TmdbMovieDetailsMapper.class);

    MovieDetailsResponse mapToMovieDetailsResponse(TmdbMovieDetailsResponse movieDetailsResponse);

}
