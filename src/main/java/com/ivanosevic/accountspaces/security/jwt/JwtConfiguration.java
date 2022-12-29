package com.ivanosevic.accountspaces.security.jwt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.temporal.ChronoUnit;

@Configuration
public class JwtConfiguration {

    @Bean(name = "verificationTokenProperties")
    public JwtProperty signInTokenProperties(@Value("${security.verification.token.secret}") String secret, @Value("${security.verification.token.expiration.time}") Integer expirationTime,
                                             @Value("${security.verification.expiration.unit}") String expirationUnit, @Value("${spring.application.name}") String issuer) {
        return new JwtProperty(secret, expirationTime, ChronoUnit.valueOf(expirationUnit), issuer);
    }

    @Bean(name = "verificationJwt")
    public Jwt verificationJwt(@Qualifier("verificationTokenProperties") JwtProperty jwtProperty) {
        return new Auth0Jwt(jwtProperty);
    }
}
