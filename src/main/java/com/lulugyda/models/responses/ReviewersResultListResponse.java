package com.lulugyda.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
public class ReviewersResultListResponse {

    @Schema(name = "Author")
    @JsonProperty("author")
    private String author;

    @Schema(name = "Rating")
    @JsonProperty("rating")
    private Double rating;

    @Schema(name = "CreatedAt")
    @JsonProperty("created_at")
    private String createdAt;

}
