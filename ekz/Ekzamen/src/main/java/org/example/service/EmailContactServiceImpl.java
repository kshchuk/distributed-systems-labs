package org.example.service;

import org.example.dao.ContactDao;
import org.example.model.EmailContact;

import java.util.UUID;

public class EmailContactServiceImpl extends EmailContactService  {
    public EmailContactServiceImpl(ContactDao dao) {
        super(dao);
    }

    @Override
    public void create(EmailContact emailContact) throws Exception {
        dao.create(emailContact);
    }

    @Override
    public EmailContact get(UUID uuid) throws Exception {
        return (EmailContact) dao.read(uuid);
    }

    @Override
    public void update(EmailContact emailContact) throws Exception {
        dao.update(emailContact);
    }

    @Override
    public boolean delete(UUID uuid) throws Exception {
        try {
            dao.delete(uuid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
