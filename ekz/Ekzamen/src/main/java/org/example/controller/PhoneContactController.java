package org.example.controller;

import org.example.dto.PhoneContactDto;
import org.example.mapper.PhoneContactMapper;
import org.example.model.Contact;
import org.example.model.PhoneContact;
import org.example.service.PhoneContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PhoneContactController extends BaseController<PhoneContact, PhoneContactDto, UUID>{
    public PhoneContactController(PhoneContactService service, PhoneContactMapper mapper) {
        super(service, mapper);
    }

    public List<PhoneContactDto> findAllByFirstName(String firstName) throws Exception {
        List<PhoneContactDto> list = new ArrayList<>();
        for (Contact phoneContact : ((PhoneContactService) service).findAllByFirstName(firstName)) {
           if (phoneContact instanceof PhoneContact) {
               list.add(mapper.toDTO((PhoneContact) phoneContact));
           }
        }
        return list;
    }

    public List<PhoneContactDto> findAllByLastName(String lastName) throws Exception {
        List<PhoneContactDto> list = new ArrayList<>();
        for (Contact phoneContact : ((PhoneContactService) service).findAllByLastName(lastName)) {
            if (phoneContact instanceof PhoneContact) {
                list.add(mapper.toDTO((PhoneContact) phoneContact));
            }
        }
        return list;
    }

    public List<PhoneContactDto> findAllByFullName(String firstName, String lastName) throws Exception {
        List<PhoneContactDto> list = new ArrayList<>();
        for (Contact phoneContact : ((PhoneContactService) service).findAllByFullName(firstName, lastName)) {
            if (phoneContact instanceof PhoneContact) {
                list.add(mapper.toDTO((PhoneContact) phoneContact));
            }
        }
        return list;
    }
}
