package com.lulugyda.controllers;

import com.lulugyda.models.entities.PhoneNumberEntity;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.services.MovieService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import static com.lulugyda.utils.Constants.USER_ID;
import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("v1/users")
@Validated
@RequiredArgsConstructor
public class UsersController {

    private final MovieService movieService;
    @Post(produces = APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<?> registerUser(@Body UserEntity user) {
        movieService.registerUser(user);
        return HttpResponse.ok();
    }

//    @Post(value = "/phone-numbers", produces = APPLICATION_JSON)
//    @ExecuteOn(TaskExecutors.IO)
//    public HttpResponse<?> addPhoneNumbers(@Body ArrayList<PhoneNumberEntity> numbers,
//                                           @Header(USER_ID) Integer userId) {
//       movieService.addPhoneNumbers(numbers,userId);
//        return HttpResponse.ok();
//    }

}
