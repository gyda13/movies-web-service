package com.lulugyda.controllers;

import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.requests.OtpRequest;
import com.lulugyda.services.MovieService;
import com.lulugyda.services.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("v1/users")
@Validated
@RequiredArgsConstructor
@Secured(value = SecurityRule.IS_ANONYMOUS)
public class UsersController {

    private final UserService userService;
    private final MovieService movieService;
    @Post(produces = APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<?> registerUser(@Body UserEntity user, @Header String phoneNumber) {
        userService.registerUser(user);
        ArrayList<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        movieService.addPhoneNumbers(phoneNumbers, user);
        return HttpResponse.ok();
    }
    @Post("/login")
    public HttpResponse<BearerAccessRefreshToken> login(@Body UsernamePasswordCredentials credentials) {
        return HttpResponse.ok(userService.login(credentials));
    }

    @Post("/verify-otp")
    public HttpResponse<BearerAccessRefreshToken> verifyOtp(
            @Body OtpRequest otpRequest,
            @Header String accessToken) {
        return HttpResponse.ok(userService.verifyOtp(otpRequest, accessToken));
    }

}
