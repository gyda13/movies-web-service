package com.lulugyda.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class MovieDetailsResponse {

    @Schema(name = "MovieId")
    @JsonProperty("id")
    private String id;

    @Schema(name = "OriginalTitle")
    @JsonProperty("original_title")
    private String originalTitle;

    @Schema(name = "OriginalLanguage")
    @JsonProperty("original_language")
    private String originalLanguage;

    @Schema(name = "ReleaseDate")
    @JsonProperty("release_date")
    private String releaseDate;

    @Schema(name = "Status")
    @JsonProperty("status")
    private String status;

    @Schema(name = "Adult")
    @JsonProperty("adult")
    private String adult;

    @Schema(name = "Genres")
    @JsonProperty("genres")
    private List<GenresResponse> genres;

    @Schema(name = "VoteAverage")
    @JsonProperty("vote_average")
    private String voteAverage;

    @Schema(name = "VoteCount")
    @JsonProperty("vote_count")
    private String voteCount;

    @Schema(name = "Budget")
    @JsonProperty("budget")
    private String budget;

    @Schema(name = "Reviewers")
    @JsonProperty("reviewers")
    private MovieReviewersResponse reviewers;

}
