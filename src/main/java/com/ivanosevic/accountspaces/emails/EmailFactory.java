package com.ivanosevic.accountspaces.emails;

import com.ivanosevic.accountspaces.emails.templates.Email;
import com.ivanosevic.accountspaces.emails.templates.NotificationEmail;
import org.springframework.stereotype.Component;

@Component
public class EmailFactory {

    private final ContextProperties contextProperties;

    public EmailFactory(ContextProperties contextProperties) {
        this.contextProperties = contextProperties;
    }


    public Email verificationEmail(String to, String accountFullname, String verifyCode) {
        var verifyAccountUrl = contextProperties.getWebsiteUrl() + "/verify-account?verificationCode=" + verifyCode;
        var title = "Verify your account - Account Spaces";
        return new NotificationEmail(to, title, accountFullname, verifyAccountUrl);
    }

    public Email basicInformationUpdatedEmail(String to, String accountFullname) {
        var message = """
                We inform you that you successfully updated the public profile of your account. If you haven't
                solicited any changes, please contact us at account-spaces.com.do
                """;
        return new NotificationEmail(to, "Basic Information Changed | Account Spaces", accountFullname, message);
    }

    public Email passwordChangedEmail(String to, String accountFullname) {
        var message = """
                We inform you that you successfully changed the password for your account. If you haven't
                solicited any changes, please contact us at support@accountspaces.com.
                """;
        return new NotificationEmail(to, "Password Changed | Account Spaces", accountFullname, message);
    }
}
