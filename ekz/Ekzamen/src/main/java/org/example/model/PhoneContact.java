package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneContact extends Contact {
    private String phoneNumber;

    public PhoneContact(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public PhoneContact() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + " " + phoneNumber;
    }
}
