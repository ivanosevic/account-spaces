package com.ivanosevic.accountspaces.accounts;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/sign-in")
    public String showSignInPage() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String showSignUpPage() {
        return "sign-up";
    }

    @GetMapping("/forgot-password")
    public String showPasswordRecoveryPage() {
        return "forgot-password";
    }

    @GetMapping("/account-spaces/my-profile")
    public String showMyProfilePage(@AuthenticationPrincipal Account signedInAccount, Model model) {
        var currentAccountData = accountService.findById(signedInAccount.getId());
        var accountBasicInformationForm = new AccountBasicInformationForm(currentAccountData.getName(), currentAccountData.getLastname(), currentAccountData.getProfileSummary());
        model.addAttribute("basicInformationForm", accountBasicInformationForm);
        var changePasswordForm = new ChangePasswordForm();
        model.addAttribute("changePasswordForm", changePasswordForm);
        return "my-profile";
    }

    @PostMapping("/account-spaces/my-profile/basic-information")
    public String updateBasicInformation(@Valid AccountBasicInformationForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal Account signedInAccount) {
        accountService.updateBasicInformation(signedInAccount.getId(), form);
        redirectAttributes.addAttribute("updatedBasicInformation", "true");
        return "redirect:/account-spaces/my-profile";
    }
}
