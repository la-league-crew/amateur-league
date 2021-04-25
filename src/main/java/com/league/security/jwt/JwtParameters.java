package com.league.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt")
public class JwtParameters {

    private String secretKey;
    private String tokenPrefix;
    private Long tokenExpirationAfterMinutes;

}
