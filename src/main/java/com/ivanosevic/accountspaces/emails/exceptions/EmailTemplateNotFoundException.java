package com.ivanosevic.accountspaces.emails.exceptions;

public class EmailTemplateNotFoundException extends RuntimeException {
    public EmailTemplateNotFoundException(String message) {
        super(message);
    }
}
