package com.lulugyda.utils;

import io.micronaut.security.authentication.Authentication;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Helper {

    public static Integer getUserId(Authentication authentication) {

        Object userIdObject = authentication.getAttributes().get("user-id");
        String userId = String.valueOf(userIdObject);

        return Integer.valueOf(userId);
    }
}
