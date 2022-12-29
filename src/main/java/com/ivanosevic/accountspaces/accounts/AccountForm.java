package com.ivanosevic.accountspaces.accounts;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.io.Serializable;
import java.util.Objects;

public class AccountForm implements Serializable {

    @NotBlank(message = "Cannot be empty.")
    @Size(max = 80, message = "Cannot have more than 80 characters.")
    private String name;

    @NotBlank(message = "Cannot be empty.")
    @Size(max = 80, message = "Cannot have more than 80 characters.")
    private String lastname;

    @Email(message = "Must be a valid E-mail Address.")
    @NotBlank(message = "Cannot be empty.")
    @UniqueEmailAddress(message = "Email Address is already in use. Please, use a different email.")
    private String emailAddress;

    @NotBlank(message = "Cannot be empty.")
    @Size(max = 32, message = "Cannot have more than 32 characters.")
    private String password;

    public AccountForm() {
    }

    public AccountForm(String name, String lastname, String emailAddress, String password) {
        this.name = name;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountForm that = (AccountForm) o;
        return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, emailAddress, password);
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
