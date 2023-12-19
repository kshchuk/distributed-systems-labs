package org.example.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data
public class PhoneContactDto extends ContactDto implements java.io.Serializable {
    private String phone;

    public PhoneContactDto() {
    }

    public PhoneContactDto(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString() + "phone: " + phone;
    }
}
