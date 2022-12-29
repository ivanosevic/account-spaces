package com.ivanosevic.accountspaces.emails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContextProperties {

    private final String websiteUrl;
    private final String emailDomain;
    private final String supportEmailAddress;
    private final String noReplyEmailAddress;

    public ContextProperties(
            @Value("${application.urls.website}") String websiteUrl,
            @Value("${application.emails.domain}") String emailDomain,
            @Value("${application.emails.support-email-address}") String supportEmailAddress,
            @Value("${application.emails.no-reply-email-address}") String noReplyEmailAddress) {
        this.websiteUrl = websiteUrl;
        this.emailDomain = emailDomain;
        this.supportEmailAddress = supportEmailAddress;
        this.noReplyEmailAddress = noReplyEmailAddress;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public String getSupportEmailAddress() {
        return supportEmailAddress;
    }

    public String getNoReplyEmailAddress() {
        return noReplyEmailAddress;
    }
}
