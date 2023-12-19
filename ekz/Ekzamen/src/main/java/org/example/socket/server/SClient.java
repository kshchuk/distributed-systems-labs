package org.example.socket.server;

import org.example.controller.EmailContactController;
import org.example.controller.PhoneContactController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SClient {

    public Socket socket;
    public ObjectInputStream cInput;
    public ObjectOutputStream cOutput;
    public ClientListenThread clientListenThread;
    public SClient(Socket socket, PhoneContactController phoneContactController, EmailContactController emailContactController) {

        try {
            this.socket = socket;
            this.cOutput = new ObjectOutputStream(this.socket.getOutputStream());
            this.cInput = new ObjectInputStream(this.socket.getInputStream());
            this.clientListenThread = new ClientListenThread(this, emailContactController, phoneContactController);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Send(Object msg)
    {
        try {
            this.cOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Listen() {
        this.clientListenThread.start();
    }
}
