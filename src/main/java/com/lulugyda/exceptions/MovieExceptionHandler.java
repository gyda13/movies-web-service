package com.lulugyda.exceptions;

import com.lulugyda.exceptions.models.UnifiedErrorResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {MovieException.class, ExceptionHandler.class})
public class MovieExceptionHandler implements ExceptionHandler<MovieException, HttpResponse<UnifiedErrorResponse>> {

    @Override
    public HttpResponse handle(HttpRequest request, MovieException exception) {

        return HttpResponse.notFound(UnifiedErrorResponse.builder()
                .generalMessage(exception.message)
                .id(exception.id)
                .build());
    }
}
