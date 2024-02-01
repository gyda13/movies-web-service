package com.lulugyda.repositories;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UsersMoviesCrudRepositoryFacade {

    private final UsersMoviesCrudRepository usersMoviesCrudRepository;

}
