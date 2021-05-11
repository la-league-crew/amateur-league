package com.league.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface LeagueUserService extends UserDetailsService {
    void disableUser(String email);
}
