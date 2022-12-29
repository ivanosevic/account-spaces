package com.ivanosevic.accountspaces.accounts;

import org.springframework.context.ApplicationEvent;

public class BasicInformationUpdatedEvent extends ApplicationEvent {

    private final Integer accountId;
    private final String fullname;
    private final String email;

    public BasicInformationUpdatedEvent(Object source, Integer accountId, String fullname, String email) {
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
