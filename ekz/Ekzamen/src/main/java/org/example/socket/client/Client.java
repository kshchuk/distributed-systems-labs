package org.example.socket.client;

import org.example.dto.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    public Socket socket;
    public ObjectInputStream sInput;
    public ObjectOutputStream sOutput;
    public String serverIP;
    public int serverPort;
    public ClientListenThread listenThread;

    public Client() {

    }

    public void Connect(String serverIP, int port) {
        try {
            System.out.println("Connecting to the server");

            this.serverIP = serverIP;
            this.serverPort = port;
            this.socket = new Socket(this.serverIP, this.serverPort);

            System.out.println("Connecting to the server");

            sOutput = new ObjectOutputStream(this.socket.getOutputStream());
            sInput = new ObjectInputStream(this.socket.getInputStream());
            listenThread = new ClientListenThread(this);

            this.listenThread.start();
        } catch (IOException ex) {
            System.out.println("Can not connected to the server.");
        }
    }

    public void Stop() {
        if (this.socket != null) {

            try {
                this.socket.close();
                this.sOutput.flush();
                this.sOutput.close();
                this.sInput.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void SendRequest(Request request) {
        try {
            this.sOutput.writeObject(request);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.Connect("localhost", 5000);

        var scanner = new Scanner(System.in);

        while (true) {


            var option = scanner.nextInt();

//            try {
//                switch (option) {
//
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
        }
    }
}
