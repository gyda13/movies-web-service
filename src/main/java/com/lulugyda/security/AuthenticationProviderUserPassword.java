package com.lulugyda.security;

import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.repositories.UsersCrudRepositoryFacade;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;

@Singleton
@RequiredArgsConstructor
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final UsersCrudRepositoryFacade usersCrudRepositoryFacade;
    private final Map<String,Object> attributes = new HashMap<>();

    @Override
    public Publisher<AuthenticationResponse> authenticate(Object httpRequest, AuthenticationRequest authenticationRequest) {

        return Flowable.create(emitter -> {
            String username = (String) authenticationRequest.getIdentity();
            String pw = (String) authenticationRequest.getSecret();

            boolean validCredentials = usersCrudRepositoryFacade.validCredentials(username, pw);
            UserEntity user = usersCrudRepositoryFacade.getUserIdByUsername(username);
            attributes.put("user-id",user.getId());

            if (validCredentials) {
                emitter.onNext(AuthenticationResponse.success(username,attributes));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }
}
