package com.league.security.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.league.models.LeagueUserCredentials;
import com.league.service.DeviceAndLocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtCredentialsDeviceAndLocationFilter extends
    UsernamePasswordAuthenticationFilter {

  private final JwtParameters jwtParameters;
  private final AuthenticationManager authenticationManager;
  private final JwtHelper jwtHelper;
  private final DeviceAndLocationService deviceAndLocationService;
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response)
      throws AuthenticationException {
    try {
      LeagueUserCredentials userCredentials = new ObjectMapper()
          .readValue(request.getInputStream(), LeagueUserCredentials.class);
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  userCredentials.getUsername(), userCredentials.getPassword()));
      if (authentication.isAuthenticated())
        deviceAndLocationService.verifyDeviceAndLocation(userCredentials.getUsername(), request);
      return authentication;
    } catch (IOException | GeoIp2Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, FilterChain chain, Authentication authResult) {
    String token = jwtHelper.generateToken(authResult);
    response.addHeader(HttpHeaders.AUTHORIZATION, jwtParameters.getTokenPrefix() + token);

  }
}
