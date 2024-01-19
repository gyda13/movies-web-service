package com.lulugyda;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Filter("/**")
public class filter implements HttpServerFilter {
    private static final Logger log = LoggerFactory.getLogger(filter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        return Flowable.defer(() -> filterRequest(request, chain)).subscribeOn(Schedulers.io());
    }

    public Publisher<MutableHttpResponse<?>> filterRequest(HttpRequest<?> request, ServerFilterChain chain) {

        return Flowable.defer(() -> {
            log.info("Before processing request: {}", request.getPath());
            return chain.proceed(request);
        }).subscribeOn(Schedulers.io()).doOnNext(response -> {

            if (response.getBody().isPresent()) {
                log.info("Response body: {}", response.getBody().get());
            }
            log.info("After processing request: {}, Status: {}", request.getPath(), response.getStatus());
        });
    }

}
