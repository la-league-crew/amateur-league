package com.league.security.jwt;

import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class JwtVerifier extends OncePerRequestFilter {

  private final JwtParameters jwtParameters;
  private final SecretKey secretKey;
  private final JwtHelper jwtHelper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (Strings.isNullOrEmpty(authHeader) || !authHeader
        .startsWith(jwtParameters.getTokenPrefix())) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      String token = authHeader.replace(jwtParameters.getTokenPrefix(), "");
      Claims body = Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
      List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
          .map(m -> new SimpleGrantedAuthority(m.get("authority")))
          .collect(Collectors.toSet());
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          body.get("email", String.class),
          null,
          simpleGrantedAuthorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtHelper.refreshToken(body);
      response.addHeader(HttpHeaders.AUTHORIZATION, jwtParameters.getTokenPrefix() + token);
    } catch (JwtException e) {
      throw new IllegalStateException("token is not valid");
    }
    filterChain.doFilter(request, response);
  }
}
