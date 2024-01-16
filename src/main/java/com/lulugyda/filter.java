package com.lulugyda;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterChain;
import io.micronaut.http.filter.HttpFilter;
import org.reactivestreams.Publisher;
@Filter("/**")
public class filter implements HttpFilter {
    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(HttpRequest<?> request, FilterChain chain) {
        System.out.println("👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻👍🏻" + request);
        return chain.proceed(request);
    }

    @Override
    public int getOrder() {
        System.out.println("🥳" + HIGHEST_PRECEDENCE);
        return HttpFilter.super.getOrder();
    }
}
