package com.ivanosevic.accountspaces.security.jwt;

import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;

public interface Jwt {
    String getToken(Map<String, Object> payload);

    String getToken(Map<String, Object> payload, Integer expirationTime, TemporalUnit temporal);

    <T> T getClaim(String token, String claim, Class<T> tClass);

    <T> List<T> getClaimAsList(String token, String claim, Class<T> tClass);

    boolean isValid(String token);
}
