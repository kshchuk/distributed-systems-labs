package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Contact implements IId<UUID>{
    protected UUID id;

    protected String firstName;
    protected String lastName;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
