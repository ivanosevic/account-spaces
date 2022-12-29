package com.ivanosevic.accountspaces.accounts;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountRoleId implements Serializable {

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "role_id")
    private Integer roleId;

    public AccountRoleId() {
    }

    public AccountRoleId(Integer accountId, Integer roleId) {
        this.accountId = accountId;
        this.roleId = roleId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRoleId that = (AccountRoleId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, roleId);
    }
}
