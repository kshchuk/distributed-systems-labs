package org.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class Contact implements IId<UUID>{
    protected UUID id;

    protected String firstName;
    protected String lastName;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = UUID.randomUUID();
    }

    public Contact() {

    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
