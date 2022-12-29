package com.ivanosevic.accountspaces.logs;

import com.ivanosevic.accountspaces.accounts.BasicInformationUpdatedEvent;
import com.ivanosevic.accountspaces.accounts.PasswordUpdatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class ActivityLogListener {
    private final ActivityLogRepository activityLogRepository;

    public ActivityLogListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void checkForBasicInformationUpdate(BasicInformationUpdatedEvent basicInformationUpdatedEvent) {
        var log = new ActivityLog(basicInformationUpdatedEvent.getAccountId(), LogType.PROFILE_CHANGE);
        activityLogRepository.save(log);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void sendPasswordChangedEmail(PasswordUpdatedEvent passwordUpdatedEvent) {
        var log = new ActivityLog(passwordUpdatedEvent.getAccountId(), LogType.PASSWORD_CHANGE);
        activityLogRepository.save(log);
    }
}
