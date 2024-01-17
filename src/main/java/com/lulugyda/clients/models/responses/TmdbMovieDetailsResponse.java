package com.lulugyda.clients.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
public class TmdbMovieDetailsResponse {

    @JsonProperty("adult")
    private String adult;

    @JsonProperty("budget")
    private String budget;

    @JsonProperty("genres")
    private List<TmdbGenresResponse> genres;

    @JsonProperty("id")
    private String id;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("title")
    private String title;

    @JsonProperty("vote_average")
    private String voteAverage;

    @JsonProperty("vote_count")
    private String voteCount;

}
