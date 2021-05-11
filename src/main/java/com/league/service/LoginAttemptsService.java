package com.league.service;

public interface LoginAttemptsService {
    void failureLoginAttempt(String username);
    void successLoginAttempt(String username);
}
