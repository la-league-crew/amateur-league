package com.league;

import com.league.security.jwt.JwtParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtParameters.class)
public class AmateurLeagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmateurLeagueApplication.class, args);
	}

}
