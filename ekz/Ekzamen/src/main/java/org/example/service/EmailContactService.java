package org.example.service;

import org.example.dao.ContactDao;
import org.example.model.EmailContact;

import java.util.UUID;

public abstract class EmailContactService extends ContactService implements Service<EmailContact, UUID> {
    protected EmailContactService(ContactDao contactDao) {
        super(contactDao);
    }
}
