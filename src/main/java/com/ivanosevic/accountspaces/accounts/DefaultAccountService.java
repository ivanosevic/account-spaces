package com.ivanosevic.accountspaces.accounts;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateBasicInformation(Integer accountId, AccountBasicInformationForm form) {
        var account = accountRepository.getReferenceById(accountId);
        account.setName(form.getName());
        account.setLastname(form.getLastname());
        account.setProfileSummary(form.getProfileSummary());
        accountRepository.save(account);
    }

    @Override
    public void changePassword(Integer accountId, ChangePasswordForm changePasswordForm) {
        var account = accountRepository.getReferenceById(accountId);
        var passwordDoesNotMatch = !passwordEncoder.matches(changePasswordForm.getPassword(), account.getPassword());
        if(passwordDoesNotMatch) {
            throw new ChangePasswordException();
        }
        var hashedPassword = passwordEncoder.encode(changePasswordForm.getNewPassword());
        account.setPassword(hashedPassword);
        accountRepository.save(account);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }
}
