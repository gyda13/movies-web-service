package com.lulugyda.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

@Introspected
@Serdeable.Serializable
public class UnifiedErrorResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("message")
    private String message;

}
