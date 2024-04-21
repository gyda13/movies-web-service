package com.lulugyda.services;

import com.lulugyda.exceptions.MovieException;
import com.lulugyda.exceptions.models.ErrorCode;
import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.requests.OtpRequest;
import com.lulugyda.repositories.UsersCrudRepositoryFacade;
import com.lulugyda.security.AuthenticationProviderUserPassword;
import com.lulugyda.security.BCryptPasswordEncoderService;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UserService {

    private final UsersCrudRepositoryFacade usersCrudRepositoryFacade;
    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;
    private final AuthenticationProviderUserPassword authenticationProvider;
    private final RedisCacheService redisCacheService;
    private final JwtTokenGenerator jwtTokenGenerator;

    public void registerUser(UserEntity userEntity) {
        String encodedPassword = bCryptPasswordEncoderService.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        usersCrudRepositoryFacade.saveUser(userEntity);

    }

    public BearerAccessRefreshToken login(UsernamePasswordCredentials credentials) {
        AuthenticationRequest<String, String> request = new UsernamePasswordCredentials(
                credentials.getUsername(), credentials.getPassword());

        AuthenticationResponse response = Single.fromPublisher(
                        authenticationProvider.authenticate(null, request)).blockingGet();

        String username = credentials.getUsername();
        UserEntity user = usersCrudRepositoryFacade.getUserIdByUsername(username);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("user-id",user.getId());
        claims.put("access_type", "limited");
        claims.put("exp", (System.currentTimeMillis() / 1000L) + 300);

        if(response.isAuthenticated()) {
            Optional<String> tokenOptional = jwtTokenGenerator.generateToken(claims);
            if (tokenOptional.isPresent()) {
                String token = tokenOptional.get();
                redisCacheService.putValue("AccessToken_" + username, token, 5L);
                BearerAccessRefreshToken bearerToken = new BearerAccessRefreshToken(
                        username, null, 300, token, null, "Bearer");
                return bearerToken;
            }
        }

        throw new MovieException(ErrorCode.LOGIN_FAILED.getId(),
                ErrorCode.LOGIN_FAILED.getMessage());
    }

    public BearerAccessRefreshToken verifyOtp(OtpRequest otpRequest, String accessToken) {

        String otpKey = "OTP_" + otpRequest.getUsername();
        String expectedOtp = redisCacheService.getValue(otpKey);
        String accessTokenKey = "AccessToken_" + otpRequest.getUsername();
        String expectedAccessToken = redisCacheService.getValue(accessTokenKey);

        UserEntity user = usersCrudRepositoryFacade.getUserIdByUsername(otpRequest.getUsername());

        if (expectedOtp != null && expectedOtp.equals(otpRequest.getOtp())
                && expectedAccessToken.equals(accessToken)) {

                Map<String, Object> claims = new HashMap<>();
                claims.put("sub", otpRequest.getUsername());
                claims.put("user-id", user.getId());
                claims.put("exp", (System.currentTimeMillis() / 1000L) + 86400);

                Optional<String> tokenOptional = jwtTokenGenerator.generateToken(claims);
                if (tokenOptional.isPresent()) {
                    String token = tokenOptional.get();
                    BearerAccessRefreshToken bearerToken = new BearerAccessRefreshToken(
                            otpRequest.getUsername(), null, 86400, token, null, "Bearer");
                    return bearerToken;
                } else {
                    throw new MovieException(ErrorCode.TOKEN_GENERATION_FAILED.getId(),
                            ErrorCode.TOKEN_GENERATION_FAILED.getMessage());
                }

        } else {
            throw new MovieException(ErrorCode.INVALID_OTP.getId(),
                    ErrorCode.INVALID_OTP.getMessage());
        }
    }

}
