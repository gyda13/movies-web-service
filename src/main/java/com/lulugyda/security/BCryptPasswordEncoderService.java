package com.lulugyda.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.inject.Singleton;
import org.springframework.security.crypto.password.PasswordEncoder;


@Singleton
public class BCryptPasswordEncoderService implements PasswordEncoder {

    PasswordEncoder delegate = new BCryptPasswordEncoder();

    @Override
    public String encode(@NotBlank @NotNull CharSequence rawPassword) {
        return delegate.encode(rawPassword);
    }

    @Override
    public boolean matches(@NotBlank @NotNull CharSequence rawPassword,
                           @NotBlank @NotNull String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}
