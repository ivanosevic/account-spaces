package com.ivanosevic.accountspaces.accounts;

import org.springframework.context.ApplicationEvent;

public class PasswordUpdatedEvent extends ApplicationEvent {

    private final Integer accountId;
    private final String fullname;
    private final String email;

    public PasswordUpdatedEvent(Object source, Integer accountId, String fullname, String email) {
        super(source);
        this.accountId = accountId;
        this.fullname = fullname;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAccountId() {
        return accountId;
    }
}
