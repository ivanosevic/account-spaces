package com.ivanosevic.accountspaces.emails;

import com.ivanosevic.accountspaces.accounts.BasicInformationUpdatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EmailEventListener {

    private final EmailFactory emailFactory;
    private final EmailService emailService;

    public EmailEventListener(EmailFactory emailFactory, EmailService emailService) {
        this.emailFactory = emailFactory;
        this.emailService = emailService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendVerificationEmail(BasicInformationUpdatedEvent basicInformationUpdatedEvent) {
        var basicInformationUpdatedEmail = emailFactory.basicInformationUpdatedEmail(basicInformationUpdatedEvent.getEmail(), basicInformationUpdatedEvent.getFullname());
        emailService.send(basicInformationUpdatedEmail);
    }
}
