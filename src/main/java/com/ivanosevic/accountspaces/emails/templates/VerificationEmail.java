package com.ivanosevic.accountspaces.emails.templates;


import com.ivanosevic.accountspaces.emails.common.EmailTemplate;

@EmailTemplate(path = "emails/verificationEmail.html")
public class VerificationEmail extends Email {

    private final String accountFullname;

    private final String verifyAccountUrl;

    public VerificationEmail(String to, String title, String accountFullname, String verifyAccountUrl) {
        super(to, title);
        this.accountFullname = accountFullname;
        this.verifyAccountUrl = verifyAccountUrl;
    }


    public String getAccountFullname() {
        return accountFullname;
    }

    public String getVerifyAccountUrl() {
        return verifyAccountUrl;
    }
}
