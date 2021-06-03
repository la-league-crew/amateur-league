package com.league.security.config;

import com.google.common.net.HttpHeaders;
import com.league.entity.LeagueUser;
import com.league.helpers.clases.OAuth2UserGoogle;
import com.league.helpers.enums.LeagueAuthProvider;
import com.league.repo.LeagueUserRepo;
import com.league.security.jwt.JwtHelper;
import com.league.security.jwt.JwtParameters;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
  private final LeagueUserRepo userRepository;
  private final JwtHelper jwtHelper;
  private final JwtParameters jwtParameters;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                  Authentication authentication) throws IOException, ServletException {
      Map<String, Object> attributes = ((DefaultOidcUser) authentication.getPrincipal()).getAttributes();
      OAuth2UserGoogle userGoogle = new OAuth2UserGoogle(attributes);
    userRepository
        .findByEmail(userGoogle.getEmail())
        .ifPresentOrElse(
            leagueUser -> {
              if (leagueUser.getAuthProvider().equals(LeagueAuthProvider.LOCAL) &&
                      !leagueUser.getAuthProvider().equals(LeagueAuthProvider.GOOGLE)) {
                leagueUser.setAuthProvider(LeagueAuthProvider.GOOGLE_AND_LOCAL);
                leagueUser = userRepository.save(leagueUser);
              }
              addTokenToHeader(leagueUser, httpServletResponse);
            },
            () -> {
              LeagueUser user = userRepository.save(userGoogle.gelLeagueUser());
              addTokenToHeader(user, httpServletResponse);
            });
  }

  private void addTokenToHeader(LeagueUser user, HttpServletResponse httpServletResponse) {
    String token = jwtHelper.generateToken(user);
    httpServletResponse.addHeader(HttpHeaders.AUTHORIZATION, jwtParameters.getTokenPrefix() + token);
  }
}
