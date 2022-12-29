package com.ivanosevic.accountspaces.accounts;

public interface AccountService {
    void updateBasicInformation(Integer accountId, AccountBasicInformationForm form);

    void changePassword(Integer accountId, ChangePasswordForm changePasswordForm);

    void createAccount(AccountForm accountForm);

    Account findById(Integer id);
}
