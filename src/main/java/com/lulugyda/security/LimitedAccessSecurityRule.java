package com.lulugyda.security;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.rules.SecurityRuleResult;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class LimitedAccessSecurityRule implements SecurityRule {

    @Override
    public int getOrder() {
        // SecurityRule.HIGHEST_PRECEDENCE: Used to specify that a security rule should run before all others.
        // evaluates this condition before any other security checks
        return SecurityRule.HIGHEST_PRECEDENCE;
    }

    @Override
    public Publisher<SecurityRuleResult> check(Object request, @Nullable Authentication authentication) {
        if (authentication != null && "limited".equals(authentication.getAttributes().get("access_type"))) {
            return Publishers.just(SecurityRuleResult.REJECTED);
        }
        return Publishers.just(SecurityRuleResult.UNKNOWN);
    }
}
