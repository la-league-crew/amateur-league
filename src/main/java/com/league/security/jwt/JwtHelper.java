package com.league.security.jwt;

import com.league.entity.LeagueUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JwtHelper {

  private final JwtParameters jwtParameters;
  private final SecretKey secretKey;

  public String generateToken(Authentication authentication) {
    LeagueUser user = (LeagueUser) authentication.getPrincipal();
    Claims claims = Jwts.claims();
    claims.put("email", user.getEmail());
    claims.put("role", user.getUserRole());
    return createNewToken(claims);
  }

  public String generateToken(LeagueUser leagueUser) {
    Claims claims = Jwts.claims();
    claims.put("email", leagueUser.getEmail() );
    claims.put("role", leagueUser.getUserRole());
    return createNewToken(claims);
  }

  public String refreshToken(Claims claims) {
    return createNewToken(claims);
  }

  private String createNewToken(Claims claims) {
    long newDateInMillSec =
        new Date().getTime() + jwtParameters.getTokenExpirationAfterMinutes() * 60000;
    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(new java.sql.Date(newDateInMillSec))
        .signWith(secretKey)
        .compact();
    return token;
  }

}
