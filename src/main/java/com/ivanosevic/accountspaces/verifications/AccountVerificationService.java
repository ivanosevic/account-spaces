package com.ivanosevic.accountspaces.verifications;

import com.ivanosevic.accountspaces.accounts.AccountCreatedEvent;
import com.ivanosevic.accountspaces.accounts.AccountRepository;
import com.ivanosevic.accountspaces.security.jwt.Jwt;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AccountVerificationService {

    private final AccountRepository accountRepository;
    private final AccountVerificationRepository accountVerificationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Jwt verificationJwt;

    public AccountVerificationService(AccountRepository accountRepository, AccountVerificationRepository accountVerificationRepository, ApplicationEventPublisher applicationEventPublisher, Jwt verificationJwt) {
        this.accountRepository = accountRepository;
        this.accountVerificationRepository = accountVerificationRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.verificationJwt = verificationJwt;
    }

    public void verifyAccount(String token) {
        if (verificationJwt.isValid(token)) {
            var emailClaim = verificationJwt.getClaim(token, "email", String.class);
            var accountVerification = accountVerificationRepository.findCurrentByToken(token).orElseThrow(AccountVerificationException::new);
            var account = accountVerification.getAccount();
            if (!account.isVerified() && account.getEmail().equals(emailClaim)) {
                // By making a simple proxy with the repository, it will generate a simple update
                // query with the 2 updated fields only.
                var accountProxy = accountRepository.getReferenceById(account.getId());
                accountProxy.setVerified(true);
                accountRepository.save(accountProxy);
            }
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void createAccountVerification(AccountCreatedEvent accountCreatedEvent) {
        var account = accountRepository.getReferenceById(accountCreatedEvent.getAccountId());
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", accountCreatedEvent.getEmailAddress());
        var token = verificationJwt.getToken(payload);

        var verification = new AccountVerification(account, token);
        accountVerificationRepository.save(verification);
        var sendEmailVerificationEvent = new VerificationCreatedEvent(this, accountCreatedEvent.getFullname(), account.getEmail(), token);
        applicationEventPublisher.publishEvent(sendEmailVerificationEvent);
    }
}
