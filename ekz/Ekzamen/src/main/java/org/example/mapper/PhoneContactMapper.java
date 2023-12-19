package org.example.mapper;

import org.example.dto.PhoneContactDto;
import org.example.model.PhoneContact;

public class PhoneContactMapper extends Mapper<PhoneContact, PhoneContactDto> {
    @Override
    public PhoneContactDto toDTO(PhoneContact entity) {
        var dto = new PhoneContactDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());

        return dto;
    }

    @Override
    public PhoneContact toEntity(PhoneContactDto phoneContactDto) {
        var phoneContact = new PhoneContact();
        phoneContact.setId(phoneContactDto.getId());
        phoneContact.setFirstName(phoneContactDto.getFirstName());
        phoneContact.setLastName(phoneContactDto.getLastName());
        phoneContact.setPhoneNumber(phoneContactDto.getPhone());

        return phoneContact;
    }
}
