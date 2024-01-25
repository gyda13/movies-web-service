package com.lulugyda.utils;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Serdeable
public enum ErrorCode {

    //Common
    INTERNAL_SERVER_ERROR("0001", "MOVIES.WEB.SERVICE.INTERNAL_SERVER_ERROR"),
    INVALID_REQUEST("0002", "MOVIES.WEB.SERVICE.INVALID_REQUEST"),
    CUSTOMER_NOT_FOUND("0003", "MOVIES.WEB.SERVICE.CUSTOMER_NOT_FOUND"),
    INVALID_CUSTOMER_IDENTIFIER("0004", "MOVIES.WEB.SERVICE.INVALID_CUSTOMER_IDENTIFIER"),
    RESOURCE_NOT_FOUND("0005", "MOVIES.WEB.SERVICE.RESOURCE_NOT_FOUND"),
    BAD_REQUEST("0006", "MOVIES.WEB.SERVICE.BAD_REQUEST"),
    INSUFFICIENT_PERMISSIONS("0007", "MOVIES.WEB.SERVICE.INSUFFICIENT_PERMISSIONS"),
    BAD_GATEWAY("0008", "MOVIES.WEB.SERVICE.BAD_GATEWAY"),
    FORBIDDEN("0009", "MOVIES.WEB.SERVICE.FORBIDDEN"),

    //TMDB
    INVALID_API_KEY("0011", "TMDB Client Error - Invalid API Key"),
    EMPTY_BODY_RECEIVED("0012", "TMDB Client Error - Empty Body Received"),
    MOVIE_DOES_NOT_EXISTS("0013","TMDB Client Error - Movie Does Not Exists");


    @Getter
    private String id;

    @Getter
    private String message;

}