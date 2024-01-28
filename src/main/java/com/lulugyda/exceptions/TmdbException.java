package com.lulugyda.exceptions;

import com.lulugyda.clients.models.responses.TmdbErrorResponse;
import com.lulugyda.exceptions.models.ErrorCode;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TmdbException {
    public static void handleTmdbException(Exception exception) {
        if (exception instanceof HttpClientResponseException e) {
            TmdbErrorResponse errorResponse = e.getResponse().getBody(TmdbErrorResponse.class).get();

            switch (errorResponse.getStatus_code()) {
                case 7:
                    throw new MovieException(ErrorCode.INVALID_API_KEY.getId(),
                            ErrorCode.INVALID_API_KEY.getMessage());
                case 34:
                    throw new MovieException(ErrorCode.MOVIE_DOES_NOT_EXISTS.getId(),
                            ErrorCode.MOVIE_DOES_NOT_EXISTS.getMessage());
            }
        }
        throw new RuntimeException();
    }
}
