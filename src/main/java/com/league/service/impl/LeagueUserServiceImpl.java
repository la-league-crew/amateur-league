package com.league.service.impl;

import com.league.repo.LeagueUserRepo;
import com.league.service.LeagueUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeagueUserServiceImpl implements LeagueUserService {

  private LeagueUserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepo.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
  }
}
