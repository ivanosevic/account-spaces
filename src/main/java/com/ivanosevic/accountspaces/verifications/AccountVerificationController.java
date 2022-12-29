package com.ivanosevic.accountspaces.verifications;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountVerificationController {

    private final AccountVerificationService accountVerificationService;

    public AccountVerificationController(AccountVerificationService accountVerificationService) {
        this.accountVerificationService = accountVerificationService;
    }

    @GetMapping("/verify-account")
    public String verifyAccount(@RequestParam String verificationCode) {
        accountVerificationService.verifyAccount(verificationCode);
        return "redirect:/sign-in";
    }

    @ExceptionHandler(value = AccountVerificationException.class)
    public String handleAccountVerificationException() {
        return "redirect:/sign-in";
    }
}
