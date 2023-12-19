package org.example.dao;

import org.example.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;

public class ContactDao implements CrudDao<Contact, UUID> {
    ReadWriteLock lock;
    private final ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    public Contact create(Contact entity) throws Exception {
        try {
            lock.writeLock().lock();
            contacts.add(entity);
            return entity;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Contact read(UUID uuid) throws Exception{
        try {
            lock.readLock().lock();
            for (Contact contact : contacts) {
                if (contact.getId().equals(uuid)) {
                    return contact;
                }
            }
            throw new NoSuchElementException("Contact with id " + uuid + " not found");
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void update(Contact entity) throws Exception {
        try {
            lock.writeLock().lock();
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getId().equals(entity.getId())) {
                    contacts.set(i, entity);
                    return;
                }
            }
            throw new NoSuchElementException("Contact with id " + entity.getId() + " not found");
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(UUID uuid) throws Exception {
        try {
            lock.writeLock().lock();
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getId().equals(uuid)) {
                    contacts.remove(i);
                    return;
                }
            }
            throw new NoSuchElementException("Contact with id " + uuid + " not found");
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Contact> findAll() throws Exception {
        try {
            lock.readLock().lock();
            return contacts;
        } finally {
            lock.readLock().unlock();
        }
    }
}
