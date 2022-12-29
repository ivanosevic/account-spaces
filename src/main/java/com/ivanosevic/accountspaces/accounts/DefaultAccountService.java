package com.ivanosevic.accountspaces.accounts;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public DefaultAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void updateBasicInformation(Integer accountId, AccountBasicInformationForm form) {
        var account = accountRepository.findByIdNoRelations(accountId).orElseThrow(AccountNotFoundException::new);
        account.setName(form.getName());
        account.setLastname(form.getLastname());
        account.setProfileSummary(form.getProfileSummary());
        accountRepository.save(account);
        var sendBasicInformationUpdatedEvent = new BasicInformationUpdatedEvent(this, accountId, account.getFullname(), account.getEmail());
        applicationEventPublisher.publishEvent(sendBasicInformationUpdatedEvent);
    }

    @Override
    public void changePassword(Integer accountId, ChangePasswordForm changePasswordForm) {
        var account = accountRepository.findByIdNoRelations(accountId).orElseThrow(AccountNotFoundException::new);
        var passwordDoesNotMatch = !passwordEncoder.matches(changePasswordForm.getPassword(), account.getPassword());
        if (passwordDoesNotMatch) {
            throw new ChangePasswordException();
        }
        var hashedPassword = passwordEncoder.encode(changePasswordForm.getNewPassword());
        account.setPassword(hashedPassword);
        accountRepository.save(account);
        var sendPasswordUpdatedEvent = new PasswordUpdatedEvent(this, accountId, account.getFullname(), account.getEmail());
        applicationEventPublisher.publishEvent(sendPasswordUpdatedEvent);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }
}
