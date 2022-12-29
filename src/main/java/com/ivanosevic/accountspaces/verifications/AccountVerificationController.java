package com.ivanosevic.accountspaces.verifications;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountVerificationController {

    private final AccountVerificationService accountVerificationService;

    public AccountVerificationController(AccountVerificationService accountVerificationService) {
        this.accountVerificationService = accountVerificationService;
    }

    @GetMapping("/verify-account")
    @ResponseStatus(HttpStatus.OK)
    public void verifyAccount(@RequestParam String verificationCode) {
        accountVerificationService.verifyAccount(verificationCode);
    }
}
