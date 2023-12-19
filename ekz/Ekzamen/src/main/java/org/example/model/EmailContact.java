package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Data
public class EmailContact extends Contact{
    private String email;

    public EmailContact(String firstName, String lastName, String email) {
        super(firstName, lastName);
        this.email = email;
    }

    public EmailContact() {

    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString() + " " + email;
    }
}
