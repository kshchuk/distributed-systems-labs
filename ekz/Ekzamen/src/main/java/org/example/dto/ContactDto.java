package org.example.dto;

import lombok.Data;
import org.example.model.IId;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Data
public abstract class ContactDto implements java.io.Serializable, IId<UUID> {
    @Nullable
    protected UUID id = null;

    protected String firstName;
    protected String lastName;

    @Override
    public String toString() {
        return "ContactDto{" +
                "id='" + (id != null ? id + "'"  : " ") +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
