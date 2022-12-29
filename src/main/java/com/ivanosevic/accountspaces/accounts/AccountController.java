package com.ivanosevic.accountspaces.accounts;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/account-spaces/my-profile")
    public String showMyProfilePage(@AuthenticationPrincipal Account signedInAccount, Model model) {
        var currentAccountData = accountService.findById(signedInAccount.getId());

        if (!model.containsAttribute("accountBasicInformationForm")) {
            var accountBasicInformationForm = new AccountBasicInformationForm(currentAccountData.getName(), currentAccountData.getLastname(), currentAccountData.getProfileSummary());
            model.addAttribute("accountBasicInformationForm", accountBasicInformationForm);
        }

        if (!model.containsAttribute("changePasswordForm")) {
            var changePasswordForm = new ChangePasswordForm();
            model.addAttribute("changePasswordForm", changePasswordForm);
        }
        return "my-profile";
    }

    @PostMapping("/account-spaces/my-profile/basic-information")
    public String updateBasicInformation(@Valid AccountBasicInformationForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal Account signedInAccount) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.accountBasicInformationForm", bindingResult);
            redirectAttributes.addFlashAttribute("accountBasicInformationForm", form);
            redirectAttributes.addAttribute("errorUpdateBasicInformation", "true");
            return "redirect:/account-spaces/my-profile";
        }
        accountService.updateBasicInformation(signedInAccount.getId(), form);
        redirectAttributes.addAttribute("updatedBasicInformation", "true");
        return "redirect:/account-spaces/my-profile";
    }

    @PostMapping("/account-spaces/change-password")
    public String updatePassword(@Valid ChangePasswordForm form, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal Account signedInAccount) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordForm", bindingResult);
            redirectAttributes.addFlashAttribute("changePasswordForm", form);
            redirectAttributes.addAttribute("errorPasswordUpdate", "true");
            return "redirect:/account-spaces/my-profile";
        }
        accountService.changePassword(signedInAccount.getId(), form);
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/sign-in";
    }

    @ExceptionHandler(value = ChangePasswordException.class)
    public String handleChangePasswordFailedException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("passwordMismatch", "true");
        return "redirect:/account-spaces/my-profile";
    }
}
