package com.ivanosevic.accountspaces.verifications;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountVerificationController {

    private final AccountVerificationService accountVerificationService;

    public AccountVerificationController(AccountVerificationService accountVerificationService) {
        this.accountVerificationService = accountVerificationService;
    }

    @GetMapping("/verify-account")
    @ResponseStatus(HttpStatus.OK)
    public String verifyAccount(@RequestParam String verificationCode) {
        accountVerificationService.verifyAccount(verificationCode);
        return "redirect:/sign-in";
    }

    @ExceptionHandler(value = AccountVerificationException.class)
    public String handleAccountVerificationException() {
        return "redirect:/sign-in";
    }
}
