package com.lulugyda.controllers;

import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.services.MovieService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("v1/users")
@Validated
@RequiredArgsConstructor
@Secured(value = SecurityRule.IS_ANONYMOUS)
public class UsersController {

    private final MovieService movieService;
    @Post(produces = APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<?> registerUser(@Body UserEntity user) {
        movieService.registerUser(user);
        return HttpResponse.ok();
    }

}
