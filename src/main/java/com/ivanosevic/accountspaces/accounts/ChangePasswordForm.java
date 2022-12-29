package com.ivanosevic.accountspaces.accounts;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class ChangePasswordForm implements Serializable {

    @NotBlank(message = "Cannot be blank.")
    private String password;

    @NotBlank(message = "Cannot be blank.")
    private String newPassword;

    public ChangePasswordForm() {
    }

    public ChangePasswordForm(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangePasswordForm that = (ChangePasswordForm) o;
        return Objects.equals(password, that.password) && Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, newPassword);
    }
}
