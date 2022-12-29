package com.ivanosevic.accountspaces.verifications;

import org.springframework.context.ApplicationEvent;

public class VerificationCreatedEvent extends ApplicationEvent {

    private final String fullname;
    private final String emailAddress;
    private final String token;

    public VerificationCreatedEvent(Object source, String fullname, String emailAddress, String token) {
        super(source);
        this.fullname = fullname;
        this.emailAddress = emailAddress;
        this.token = token;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getToken() {
        return token;
    }
}
