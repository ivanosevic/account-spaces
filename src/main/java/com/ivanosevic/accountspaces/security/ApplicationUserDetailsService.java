package com.ivanosevic.accountspaces.security;

import com.ivanosevic.accountspaces.accounts.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public ApplicationUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountDetailsByEmail(username).orElseThrow(() -> {
            var errorMessage = String.format("The username %s was not found. Thus, it was not loaded through the UserDetailsService.", username);
            throw new UsernameNotFoundException(errorMessage);
        });
    }
}
