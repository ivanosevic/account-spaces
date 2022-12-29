package com.ivanosevic.accountspaces.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RememberMeProperties {

    private final String key;
    private final Integer duration;

    public RememberMeProperties(@Value("${server.cookies.remember-me.key}") String key,
                                @Value("${server.cookies.remember-me.duration}") Integer duration) {
        this.key = key;
        this.duration = duration;
    }

    public String getKey() {
        return key;
    }

    public Integer getDuration() {
        return duration;
    }
}
