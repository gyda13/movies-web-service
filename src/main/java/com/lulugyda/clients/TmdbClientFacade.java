package com.lulugyda.clients;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class TmdbClientFacade {

    private final TmdbClient tmdbClient;


}
