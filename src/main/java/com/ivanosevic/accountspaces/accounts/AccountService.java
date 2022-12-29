package com.ivanosevic.accountspaces.accounts;

public interface AccountService {
    void updateBasicInformation(Integer accountId, AccountBasicInformationForm form);
    void changePassword(Integer accountId, ChangePasswordForm changePasswordForm);
    Account findById(Integer id);
}
