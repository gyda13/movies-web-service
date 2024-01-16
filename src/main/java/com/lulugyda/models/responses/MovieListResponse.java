package com.lulugyda.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
public class MovieListResponse {
    @JsonProperty("page")
    private String page;

    @JsonProperty("results")
    private ArrayList<ResultsResponse> results;

    @JsonProperty("total_pages")
    private String total_pages;

    @JsonProperty("total_results")
    private String total_results;
}
