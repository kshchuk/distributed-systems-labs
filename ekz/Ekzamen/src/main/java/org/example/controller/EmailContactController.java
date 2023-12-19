package org.example.controller;

import org.example.dto.EmailContactDto;
import org.example.mapper.EmailContactMapper;
import org.example.model.Contact;
import org.example.model.EmailContact;
import org.example.service.EmailContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmailContactController extends BaseController<EmailContact, EmailContactDto, UUID>{
    public EmailContactController(EmailContactService service, EmailContactMapper mapper) {
        super(service, mapper);
    }

    public List<?> findByFirstName(String firstName) throws Exception {
        List<EmailContactDto> list = new ArrayList<>();
        for (Contact emailContact : ((EmailContactService) service).findAllByFirstName(firstName)) {
            if (emailContact instanceof EmailContact) {
                list.add(mapper.toDTO((EmailContact) emailContact));
            }
        }

        return list;
    }

    public List<?> findByLastName(String lastName) throws Exception {
        List<EmailContactDto> list = new ArrayList<>();
        for (Contact emailContact : ((EmailContactService) service).findAllByLastName(lastName)) {
            if (emailContact instanceof EmailContact) {
                list.add(mapper.toDTO((EmailContact) emailContact));
            }
        }

        return list;
    }

    public List<?> findByFullName(String firstName, String lastName) throws Exception {
        List<EmailContactDto> list = new ArrayList<>();
        for (Contact emailContact : ((EmailContactService) service).findAllByFullName(firstName, lastName)) {
            if (emailContact instanceof EmailContact) {
                list.add(mapper.toDTO((EmailContact) emailContact));
            }
        }

        return list;
    }
}
