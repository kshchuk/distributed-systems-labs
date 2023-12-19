package org.example.dto;

import lombok.Data;

@Data
public class EmailContactDto extends ContactDto implements java.io.Serializable {
    private String email;

    public EmailContactDto() {
    }

    public EmailContactDto(String firstName, String lastName, String email) {
        super(firstName, lastName);
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + " " + email;
    }
}
