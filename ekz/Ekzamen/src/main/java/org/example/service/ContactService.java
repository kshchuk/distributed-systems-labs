package org.example.service;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import java.util.ArrayList;
import java.util.List;

public abstract class ContactService {
    protected final ContactDao dao;

    protected ContactService(ContactDao contactDao) {
        this.dao = contactDao;
    }

    public List<Contact> findAllByFirstName(String firstName) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : dao.findAll()) {
            if (contact.getFirstName().equals(firstName)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
    public List<Contact> findAllByLastName(String lastName) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : dao.findAll()) {
            if (contact.getLastName().equals(lastName)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
    public List<Contact> findAllByFullName(String firstName, String lastName) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : dao.findAll()) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
}
