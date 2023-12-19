package org.example.dao;

import org.example.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ContactDao implements CrudDao<Contact, UUID> {
    private ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    public Contact create(Contact entity) throws Exception {
        contacts.add(entity);
        return entity;
    }

    @Override
    public Contact read(UUID uuid) throws Exception{
        for (Contact contact : contacts) {
            if (contact.getId().equals(uuid)) {
                return contact;
            }
        }
        throw new NoSuchElementException("Contact with id " + uuid + " not found");
    }

    @Override
    public void update(Contact entity) throws Exception {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(entity.getId())) {
                contacts.set(i, entity);
                return;
            }
        }
        throw new NoSuchElementException("Contact with id " + entity.getId() + " not found");
    }

    @Override
    public void delete(UUID uuid) throws Exception {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(uuid)) {
                contacts.remove(i);
                return;
            }
        }
        throw new NoSuchElementException("Contact with id " + uuid + " not found");
    }

    @Override
    public List<Contact> findAll() throws Exception {
        return contacts;
    }
}
