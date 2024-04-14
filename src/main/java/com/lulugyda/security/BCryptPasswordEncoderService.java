package com.lulugyda.security;

import com.lulugyda.exceptions.WrongPasswordException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.inject.Singleton;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.lulugyda.exceptions.ExceptionManager.handleException;


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
        try {
            if (!delegate.matches(rawPassword, encodedPassword)){
                throw new WrongPasswordException();
            }

        } catch (Exception exception) {
            handleException(exception);
        }
        return delegate.matches(rawPassword, encodedPassword);
    }
}
