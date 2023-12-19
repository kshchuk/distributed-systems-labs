package org.example.mapper;

import org.example.dto.EmailContactDto;
import org.example.model.EmailContact;

public class EmailContactMapper extends Mapper<EmailContact, EmailContactDto> {
    @Override
    public EmailContactDto toDTO(EmailContact entity) {
        var dto = new EmailContactDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    @Override
    public EmailContact toEntity(EmailContactDto emailContactDto) {
        var emailContact = new EmailContact();
        emailContact.setId(emailContactDto.getId());
        emailContact.setFirstName(emailContactDto.getFirstName());
        emailContact.setLastName(emailContactDto.getLastName());
        emailContact.setEmail(emailContactDto.getEmail());

        return emailContact;
    }
}
