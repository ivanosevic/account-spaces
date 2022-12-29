package com.ivanosevic.accountspaces.security.jwt;

import java.time.temporal.ChronoUnit;

public record JwtProperty(String secret, Integer expirationTime, ChronoUnit expirationTimeUnit, String issuer) {

}
