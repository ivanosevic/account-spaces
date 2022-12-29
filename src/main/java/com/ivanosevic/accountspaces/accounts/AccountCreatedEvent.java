package com.ivanosevic.accountspaces.accounts;

import org.springframework.context.ApplicationEvent;

public class AccountCreatedEvent extends ApplicationEvent {

    private final Integer accountId;
    private final String fullname;
    private final String emailAddress;
    private final String username;

    public AccountCreatedEvent(Object source, Integer accountId, String fullname, String emailAddress, String username) {
        super(source);
        this.accountId = accountId;
        this.fullname = fullname;
        this.emailAddress = emailAddress;
        this.username = username;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }
}
