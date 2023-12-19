package org.example.service;

import org.example.dao.ContactDao;
import org.example.model.PhoneContact;

import java.util.UUID;

public class PhoneContactServiceImpl extends PhoneContactService {
    public PhoneContactServiceImpl(ContactDao dao) {
        super(dao);
    }

    @Override
    public void create(PhoneContact phoneContact) throws Exception {
        dao.create(phoneContact);
    }

    @Override
    public PhoneContact get(UUID uuid) throws Exception {
        try {
            return (PhoneContact) dao.read(uuid);
        } catch (Exception e) {
            throw new Exception("Contact not found");
    }
    }


    @Override
    public void update(PhoneContact phoneContact) throws Exception {
        dao.update(phoneContact);
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
