package com.league.service.impl;

import com.league.entity.LoginAttempts;
import com.league.repo.LoginAttemptsRepo;
import com.league.service.LeagueUserService;
import com.league.service.LoginAttemptsService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class LoginAttemptsServiceImpl implements LoginAttemptsService {

  private final LeagueUserService leagueUserService;
  private final LoginAttemptsRepo loginAttemptsRepo;
  private static final int MAX_FAILURE_ATTEMPTS = 3;

  @Override
  public void failureLoginAttempt(String username) {
    try {
      UserDetails userDetails = leagueUserService.loadUserByUsername(username);
      // TODO exception handling
      if (!userDetails.isEnabled()) throw new IllegalStateException("Account is not enabled");
      loginAttemptsRepo
          .findByUsername(userDetails.getUsername())
          .ifPresentOrElse(
              loginAttempts -> {
                if (loginAttempts.getNumberOfFailureAttempts() < MAX_FAILURE_ATTEMPTS)
                  loginAttempts.increaseNumberOfFailureAttempts();
                if (loginAttempts.getNumberOfFailureAttempts() == MAX_FAILURE_ATTEMPTS)
                  leagueUserService.disableUser(userDetails.getUsername());
                loginAttempts.setDateOfLastAttempt(LocalDateTime.now());
                loginAttemptsRepo.save(loginAttempts);
              },
              () ->
                  loginAttemptsRepo.save(
                      LoginAttempts.builder()
                          .username(username)
                          .numberOfFailureAttempts(1)
                          .dateOfLastAttempt(LocalDateTime.now())
                          .build()));
    } catch (UsernameNotFoundException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void successLoginAttempt(String username) {
    loginAttemptsRepo
        .findByUsername(username)
        .ifPresentOrElse(
            loginAttempts -> {
              loginAttempts.resetNumberOfFailureAttempts();
              loginAttemptsRepo.save(loginAttempts);
            },
            () ->
                loginAttemptsRepo.save(
                    LoginAttempts.builder()
                        .username(username)
                        .dateOfLastAttempt(LocalDateTime.now())
                        .build()));
  }
}
