package com.ivanosevic.accountspaces.accounts;

import org.springframework.context.ApplicationEvent;

public class BasicInformationUpdatedEvent extends ApplicationEvent {

    private final String fullname;
    private final String email;

    public BasicInformationUpdatedEvent(Object source, String fullname, String email) {
        super(source);
        this.fullname = fullname;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
}
