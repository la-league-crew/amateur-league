package com.league.security.config;

import com.league.service.LoginAttemptsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LeagueAuthenticationEventPublisher implements AuthenticationEventPublisher {
    private LoginAttemptsService loginAttemptsService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        loginAttemptsService.successLoginAttempt(authentication.getName());
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        loginAttemptsService.failureLoginAttempt(authentication.getName());
    }
}
