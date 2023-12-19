package org.example.rmi.server;

import org.example.controller.EmailContactController;
import org.example.controller.PhoneContactController;
import org.example.dao.ContactDao;
import org.example.dto.EmailContactDto;
import org.example.dto.PhoneContactDto;
import org.example.mapper.EmailContactMapper;
import org.example.mapper.PhoneContactMapper;
import org.example.service.EmailContactService;
import org.example.service.EmailContactServiceImpl;
import org.example.service.PhoneContactService;
import org.example.service.PhoneContactServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RMIServerImpl extends UnicastRemoteObject implements RMIServer {
    private final EmailContactController emailContactController;
    private final PhoneContactController phoneContactController;

    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     *
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since 1.1
     */
    protected RMIServerImpl() throws RemoteException, SQLException {
        var dao = new ContactDao();
        EmailContactService emailContactService = new EmailContactServiceImpl(dao);
        PhoneContactService phoneContactService = new PhoneContactServiceImpl(dao);

        var phoneContactMapper = new PhoneContactMapper();
        var emailContactMapper = new EmailContactMapper();

        this.emailContactController = new EmailContactController(emailContactService, emailContactMapper);
        this.phoneContactController = new PhoneContactController(phoneContactService, phoneContactMapper);
    }

    @Override
    public EmailContactDto createEmailContact(EmailContactDto emailContactDto) throws Exception {
        return emailContactController.create(emailContactDto);
    }

    @Override
    public EmailContactDto readEmailContact(UUID id) throws Exception {
        return emailContactController.get(id);
    }

    @Override
    public EmailContactDto updateEmailContact(EmailContactDto emailContactDto) throws Exception {
        emailContactController.update(emailContactDto);
        return emailContactDto;
    }

    @Override
    public void deleteEmailContact(UUID id) throws Exception {
        emailContactController.delete(id);
    }

    @Override
    public PhoneContactDto createPhoneContact(PhoneContactDto phoneContactDto) throws Exception {
        return phoneContactController.create(phoneContactDto);
    }

    @Override
    public PhoneContactDto readPhoneContact(UUID id) throws Exception {
        return phoneContactController.get(id);
    }

    @Override
    public PhoneContactDto updatePhoneContact(PhoneContactDto phoneContactDto) throws Exception {
        phoneContactController.update(phoneContactDto);
        return phoneContactDto;
    }

    @Override
    public void deletePhoneContact(UUID id) throws Exception {
        phoneContactController.delete(id);
    }

    @Override
    public List<EmailContactDto> findAllEmailContactsByFirstName(String firstName) throws Exception {
        return (List<EmailContactDto>) emailContactController.findByFirstName(firstName);
    }

    @Override
    public List<EmailContactDto> findAllEmailContactsByLastName(String lastName) throws Exception {
        return (List<EmailContactDto>) emailContactController.findByLastName(lastName);
    }

    @Override
    public List<EmailContactDto> findAllEmailContactsByFullName(String firstName, String lastName) throws Exception {
        return (List<EmailContactDto>) emailContactController.findByFullName(firstName, lastName);
    }

    @Override
    public List<PhoneContactDto> findAllPhoneContactsByFirstName(String firstName) throws Exception {
        return (List<PhoneContactDto>) phoneContactController.findAllByFirstName(firstName);
    }

    @Override
    public List<PhoneContactDto> findAllPhoneContactsByLastName(String lastName) throws Exception {
        return (List<PhoneContactDto>) phoneContactController.findAllByLastName(lastName);
    }

    @Override
    public List<PhoneContactDto> findAllPhoneContactsByFullName(String firstName, String lastName) throws Exception {
        return (List<PhoneContactDto>) phoneContactController.findAllByFullName(firstName, lastName);
    }
}
