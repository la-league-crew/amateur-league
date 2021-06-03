package com.league.security.config;

import com.league.security.jwt.JwtCredentialsDeviceAndLocationFilter;
import com.league.security.jwt.JwtHelper;
import com.league.security.jwt.JwtParameters;
import com.league.security.jwt.JwtVerifier;
import javax.crypto.SecretKey;

import com.league.service.DeviceAndLocationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService leagueUserService;
  private final JwtParameters jwtParameters;
  private final SecretKey secretKey;
  private final JwtHelper jwtHelper;
  private final LeagueAuthenticationEventPublisher leagueAuthenticationEventPublisher;
  private final DeviceAndLocationService deviceAndLocationService;
  private final  AuthenticationSuccessHandler authenticationSuccessHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(
            new JwtCredentialsDeviceAndLocationFilter(jwtParameters, authenticationManager(),
                jwtHelper,deviceAndLocationService))
        .addFilterAfter(new JwtVerifier(jwtParameters, secretKey, jwtHelper),
            JwtCredentialsDeviceAndLocationFilter.class)
        .authorizeRequests()
        .anyRequest()
        .authenticated()
            .and()
            .oauth2Login()
            .successHandler(authenticationSuccessHandler);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder());
    provider.setUserDetailsService(leagueUserService);
    auth.authenticationProvider(provider);
    auth.authenticationEventPublisher(leagueAuthenticationEventPublisher);
  }
}
