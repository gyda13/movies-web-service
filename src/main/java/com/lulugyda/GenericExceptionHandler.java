package com.lulugyda;

import com.lulugyda.utils.ErrorCode;
import com.lulugyda.utils.UnifiedErrorResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Produces
@Singleton
@Slf4j
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class GenericExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {

    @Override
    public HttpResponse<UnifiedErrorResponse> handle(HttpRequest request, Exception exception) {
        return HttpResponse.notFound(UnifiedErrorResponse.builder()
                .message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .id(ErrorCode.INTERNAL_SERVER_ERROR.getId())
                .build());
    }
}