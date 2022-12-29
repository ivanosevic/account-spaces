package com.ivanosevic.accountspaces.accounts;

import com.ivanosevic.accountspaces.roles.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class AccountRole implements Serializable {

    @EmbeddedId
    private AccountRoleId accountRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;

    public AccountRole() {
        this.accountRoleId = new AccountRoleId();
    }

    public AccountRole(Account account, Role role) {
        this.accountRoleId = new AccountRoleId();
        this.account = account;
        this.role = role;
    }

    public AccountRoleId getAccountRoleId() {
        return accountRoleId;
    }

    public void setAccountRoleId(AccountRoleId accountRoleId) {
        this.accountRoleId = accountRoleId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRole that = (AccountRole) o;
        return Objects.equals(account, that.account) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, role);
    }
}