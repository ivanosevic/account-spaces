package com.ivanosevic.accountspaces.emails.templates;

import com.ivanosevic.accountspaces.emails.common.EmailTemplate;

@EmailTemplate(path = "emails/notificationEmail.html")
public class NotificationEmail extends Email {

    private final String accountFullname;
    private final String message;

    public NotificationEmail(String to, String title, String accountFullname, String message) {
        super(to, title);
        this.accountFullname = accountFullname;
        this.message = message;
    }


    public String getAccountFullname() {
        return accountFullname;
    }

    public String getMessage() {
        return message;
    }
}
