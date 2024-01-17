package com.lulugyda.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
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
public class ReviewersResponse {

    @JsonProperty("author")
    private String author;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private String createdAt;

}
