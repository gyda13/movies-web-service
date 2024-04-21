package com.lulugyda.security;

import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.repositories.UsersCrudRepositoryFacade;
import com.lulugyda.services.RedisCacheService;
import com.lulugyda.services.TwilioService;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

import java.util.*;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final UsersCrudRepositoryFacade usersCrudRepositoryFacade;
    private final Map<String,Object> attributes = new HashMap<>();

    private final RedisCacheService redisCacheService;

    private final TwilioService twilioService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(Object httpRequest, AuthenticationRequest authenticationRequest) {

        return Flowable.create(emitter -> {
            String username = (String) authenticationRequest.getIdentity();
            String pw = (String) authenticationRequest.getSecret();

            boolean validCredentials = usersCrudRepositoryFacade.validCredentials(username, pw);
            UserEntity user = usersCrudRepositoryFacade.getUserIdByUsername(username);
            attributes.put("user-id",user.getId());
            attributes.put("message", "OTP sent");

            if (validCredentials) {
                String otp = generateOtp();
                redisCacheService.putValue("OTP_" + username, otp, 5L);

                String phoneNumber = user.getPhoneNumbers().get(0).getMobileNumber();
                twilioService.sendOtpMessage(phoneNumber, otp);
                log.error("sendOTP:: OTP sent to {}" , phoneNumber);

                emitter.onNext(AuthenticationResponse.success(username,attributes));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }

    private String generateOtp() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

}
