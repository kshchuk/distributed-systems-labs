package org.example.service;

import org.example.dao.ContactDao;
import org.example.model.PhoneContact;

import java.util.UUID;

public abstract class PhoneContactService extends ContactService implements Service<PhoneContact, UUID>{
    protected PhoneContactService(ContactDao contactDao) {
        super(contactDao);
    }
}
