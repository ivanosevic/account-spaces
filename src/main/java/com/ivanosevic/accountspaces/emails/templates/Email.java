package com.ivanosevic.accountspaces.emails.templates;

import java.util.ArrayList;
import java.util.List;

public abstract class Email {

    private final String title;
    private final String to;
    private final List<String> carbonCopy;

    public Email(String to, String title) {
        this.to = to;
        this.title = title;
        this.carbonCopy = new ArrayList<>();
    }

    public boolean hasCarbonCopy() {
        return !carbonCopy.isEmpty();
    }

    public void addCarbonCopy(String email) {
        carbonCopy.add(email);
    }

    public String getTitle() {
        return title;
    }

    public String getTo() {
        return to;
    }

    public List<String> getCarbonCopy() {
        return carbonCopy;
    }
}
