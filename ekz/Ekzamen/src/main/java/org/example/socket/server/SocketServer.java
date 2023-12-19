package org.example.socket.server;

import org.example.controller.EmailContactController;
import org.example.controller.PhoneContactController;
import org.example.dao.ContactDao;
import org.example.mapper.EmailContactMapper;
import org.example.mapper.PhoneContactMapper;
import org.example.service.EmailContactService;
import org.example.service.EmailContactServiceImpl;
import org.example.service.PhoneContactService;
import org.example.service.PhoneContactServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Logger;

//This is a TCP protocol connection based server.
public class SocketServer {
    public PhoneContactController phoneContactController;
    public EmailContactController emailContactController;

    public ServerSocket socket;
    public int port;
    public ListenConnectionRequestThread listenConnectionRequestThread;
    public ClientRemovingControlThread removingControlThread;
    public static ArrayList<SClient> clients;

    public SocketServer(int port, PhoneContactController phoneContactController, EmailContactController emailContactController) {
        try {
            this.port = port;
            this.phoneContactController = phoneContactController;
            this.emailContactController = emailContactController;
            this.socket = new ServerSocket(this.port);
            this.listenConnectionRequestThread = new ListenConnectionRequestThread(this);
            removingControlThread = new ClientRemovingControlThread(this);
            clients = new ArrayList<>();
        } catch (IOException e) {
            Logger.getLogger(SocketServer.class.getName()).info("Server could not be started on port: " + this.port);

        }
    }

    public void start() {
        this.listenConnectionRequestThread.start();
        this.removingControlThread.start();
        Logger.getLogger(SocketServer.class.getName()).info("Server started on port: " + this.port);
    }

    public void Stop() {
        try {
            this.socket.close();
            this.listenConnectionRequestThread.interrupt();
            this.removingControlThread.interrupt();
        } catch (IOException e) {
            Logger.getLogger(SocketServer.class.getName()).info("Server could not be stopped on port: " + this.port);
        }
    }

    public static void main(String[] args) throws Exception {
        var dao = new ContactDao();
        EmailContactService emailContactService = new EmailContactServiceImpl(dao);
        PhoneContactService phoneContactService = new PhoneContactServiceImpl(dao);

        var phoneContactMapper = new PhoneContactMapper();
        var emailContactMapper = new EmailContactMapper();

        var emailContactController = new EmailContactController(emailContactService, emailContactMapper);
        var phoneContactController = new PhoneContactController(phoneContactService, phoneContactMapper);

        SocketServer server = new SocketServer(5000, phoneContactController, emailContactController);
        server.start();
    }
}
