package com.ivanosevic.accountspaces.logs;

import com.ivanosevic.accountspaces.accounts.Account;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivityLogController {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogController(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @GetMapping("/account-spaces/activity-log")
    public String showActivityLogPage(@RequestParam(defaultValue = "10") Integer numberOfPages, @RequestParam(defaultValue = "0") Integer pageNumber, @AuthenticationPrincipal Account account, Model model) {
        var activityLogPage = activityLogRepository.findByAccountId(account.getId(), PageRequest.of(pageNumber, numberOfPages, Sort.by("createdAt").descending()));
        model.addAttribute("activityLogPage", activityLogPage);
        return "activity-log";
    }
}
