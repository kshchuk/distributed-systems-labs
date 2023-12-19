package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailContact extends Contact{
    private String email;

    public EmailContact(String firstName, String lastName, String email) {
        super(firstName, lastName);
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + " " + email;
    }
}
