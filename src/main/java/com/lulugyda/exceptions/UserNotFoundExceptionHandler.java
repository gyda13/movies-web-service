package com.lulugyda.exceptions;

import com.lulugyda.exceptions.models.ErrorCode;
import com.lulugyda.exceptions.models.UnifiedErrorResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {UserNotFoundException.class, ExceptionHandler.class})
public class UserNotFoundExceptionHandler implements ExceptionHandler<UserNotFoundException, HttpResponse<UnifiedErrorResponse>> {

    @Override
    public HttpResponse handle(HttpRequest request, UserNotFoundException exception) {

        return HttpResponse.notFound(UnifiedErrorResponse.builder()
                .generalMessage(ErrorCode.USER_NOT_FOUND.getMessage())
                 .id(ErrorCode.USER_NOT_FOUND.getId())
                .build());
    }

}
