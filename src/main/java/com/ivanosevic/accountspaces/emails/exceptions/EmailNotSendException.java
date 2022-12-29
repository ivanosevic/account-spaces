package com.ivanosevic.accountspaces.emails.exceptions;

public class EmailNotSendException extends RuntimeException {
    public EmailNotSendException(String emailClassName, String exMsg) {
        super(String.format("Email: [%s] couldn't be sent due to: [%s]", emailClassName, exMsg));
    }
}
