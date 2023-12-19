package org.example.controller;

import org.example.dto.EmailContactDto;
import org.example.dto.PhoneContactDto;

import java.rmi.Remote;
import java.util.List;
import java.util.UUID;

public interface MergedController extends Remote {
    EmailContactDto createEmailContact(EmailContactDto emailContactDto) throws Exception;
    EmailContactDto readEmailContact(UUID id) throws Exception;
    EmailContactDto updateEmailContact(EmailContactDto emailContactDto) throws Exception;
    void deleteEmailContact(UUID id) throws Exception;
    PhoneContactDto createPhoneContact(PhoneContactDto phoneContactDto) throws Exception;
    PhoneContactDto readPhoneContact(UUID id) throws Exception;
    PhoneContactDto updatePhoneContact(PhoneContactDto phoneContactDto) throws Exception;
    void deletePhoneContact(UUID id) throws Exception;
    List<EmailContactDto> findAllEmailContactsByFirstName(String firstName) throws Exception;
    List<EmailContactDto> findAllEmailContactsByLastName(String lastName) throws Exception;
    List<EmailContactDto> findAllEmailContactsByFullName(String firstName, String lastName) throws Exception;
    List<PhoneContactDto> findAllPhoneContactsByFirstName(String firstName) throws Exception;
    List<PhoneContactDto> findAllPhoneContactsByLastName(String lastName) throws Exception;
    List<PhoneContactDto> findAllPhoneContactsByFullName(String firstName, String lastName) throws Exception;
}
