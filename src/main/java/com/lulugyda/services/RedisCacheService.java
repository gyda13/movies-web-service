package com.lulugyda.services;

import io.lettuce.core.api.StatefulRedisConnection;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Singleton
@RequiredArgsConstructor
public class RedisCacheService {

    @Inject
    private final StatefulRedisConnection<String, String> connection;

    public void putValue(String key, String value) {
        connection.sync().setex(key, Duration.ofHours(24).getSeconds(), value);
    }

    public String getValue(String key) {
        return connection.sync().get(key);
    }

}
