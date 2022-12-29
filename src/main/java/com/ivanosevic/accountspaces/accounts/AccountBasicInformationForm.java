package com.ivanosevic.accountspaces.accounts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class AccountBasicInformationForm implements Serializable {

    @NotBlank(message = "Cannot be blank.")
    @Size(max = 80, message = "Cannot exceed 80 characters")
    private String name;

    @NotBlank(message = "Cannot be blank.")
    @Size(max = 80, message = "Cannot exceed 80 characters.")
    private String lastname;

    @Size(max = 200, message = "Cannot exceed 200 characters.")
    private String profileSummary;

    public AccountBasicInformationForm() {
    }

    public AccountBasicInformationForm(String name, String lastname, String profileSummary) {
        this.name = name;
        this.lastname = lastname;
        this.profileSummary = profileSummary;
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

    public String getProfileSummary() {
        return profileSummary;
    }

    public void setProfileSummary(String profileSummary) {
        this.profileSummary = profileSummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBasicInformationForm that = (AccountBasicInformationForm) o;
        return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(profileSummary, that.profileSummary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, profileSummary);
    }
}
