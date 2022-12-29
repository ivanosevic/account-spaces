package com.ivanosevic.accountspaces.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;

public class Auth0Jwt implements Jwt {

    private final Algorithm signInAlgorithm;
    private final JWTVerifier verifier;
    private final JwtProperty jwtProperty;

    public Auth0Jwt(JwtProperty jwtProperty) {
        this.jwtProperty = jwtProperty;
        this.signInAlgorithm = Algorithm.HMAC256(jwtProperty.secret().getBytes());
        this.verifier = JWT.require(signInAlgorithm).withIssuer(jwtProperty.issuer()).build();
    }

    @Override
    public String getToken(Map<String, Object> payload) {
        var tokenIssuedAt = Instant.now();
        var tokenExpiresAt = tokenIssuedAt.plus(jwtProperty.expirationTime(), jwtProperty.expirationTimeUnit());
        return JWT.create()
                .withPayload(payload)
                .withIssuedAt(tokenIssuedAt)
                .withExpiresAt(tokenExpiresAt)
                .withIssuer(jwtProperty.issuer())
                .sign(signInAlgorithm);
    }

    @Override
    public String getToken(Map<String, Object> payload, Integer expirationTime, TemporalUnit temporal) {
        var tokenIssuedAt = Instant.now();
        var tokenExpiresAt = tokenIssuedAt.plus(expirationTime, temporal);
        return JWT.create()
                .withPayload(payload)
                .withIssuedAt(tokenIssuedAt)
                .withExpiresAt(tokenExpiresAt)
                .withIssuer(jwtProperty.issuer())
                .sign(signInAlgorithm);
    }

    @Override
    public <T> T getClaim(String token, String claim, Class<T> tClass) {
        var decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims().get(claim).as(tClass);
    }

    @Override
    public <T> List<T> getClaimAsList(String token, String claim, Class<T> tClass) {
        var decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims().get(claim).asList(tClass);
    }

    @Override
    public boolean isValid(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }
}
