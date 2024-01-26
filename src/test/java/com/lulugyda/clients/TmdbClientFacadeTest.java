package com.lulugyda.clients;

import com.lulugyda.clients.models.responses.TmdbMovieDetailsResponse;
import com.lulugyda.clients.models.responses.TmdbMovieListResponse;
import com.lulugyda.clients.models.responses.TmdbMovieReviewersResponse;
import com.lulugyda.clients.models.responses.TmdbResultsResponse;
import com.lulugyda.clients.models.responses.TmdbReviewersResultListResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TmdbClientFacadeTest {

    @Inject
    TmdbClientFacade tmdbClientFacade;

    @Inject
    TmdbClient tmdbClient;

    @MockBean(TmdbClient.class)
    TmdbClient tmdbClient() {
        return Mockito.mock(TmdbClient.class);
    }

    public static final String MOVIE_ID = "123";

    @Test
    void testGetMovieListSuccess() {

        when(tmdbClient.getMovieList(any(), any())).thenReturn(
                HttpResponse.ok(TmdbMovieListResponse.builder().page("3").build())
        );

        TmdbMovieListResponse response = tmdbClientFacade.getMovieList("3");

        Assertions.assertEquals("3", response.getPage());

    }

    @Test
    void testGetMovieDetailsSuccess() {

        when(tmdbClient.getMovieDetails(any(), any())).thenReturn(
                HttpResponse.ok(TmdbMovieDetailsResponse.builder().id(MOVIE_ID).build())
        );

        TmdbMovieDetailsResponse response = tmdbClientFacade.getMovieDetails(MOVIE_ID);

        Assertions.assertEquals(MOVIE_ID, response.getId());

    }

    @Test
    void testGetSimilarMoviesSuccess() {

        when(tmdbClient.getSimilarMovies(any(), any())).thenReturn(
                HttpResponse.ok(TmdbMovieListResponse.builder()
                        .results(List.of(TmdbResultsResponse.builder().id(MOVIE_ID).build()))
                        .build())
        );

        TmdbMovieListResponse response = tmdbClientFacade.getSimilarMovies(MOVIE_ID);

        Assertions.assertEquals(MOVIE_ID, response.getResults().get(0).getId());

    }

    @Test
    void testGetMovieReviewersSuccess() {

        when(tmdbClient.getMovieReviewers(any(), any())).thenReturn(
                HttpResponse.ok(TmdbMovieReviewersResponse.builder()
                        .results(List.of(TmdbReviewersResultListResponse.builder()
                                .author("gyda")
                                .build()))
                        .build())
        );

        TmdbMovieReviewersResponse response = tmdbClientFacade.getMovieReviewers(MOVIE_ID);

        Assertions.assertEquals("gyda", response.getResults().get(0).getAuthor());

    }

}
