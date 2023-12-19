package org.example.socket.client;

import org.example.dto.EmailContactDto;
import org.example.dto.PhoneContactDto;
import org.example.dto.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
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
            System.out.println("1. Create email contact");
            System.out.println("2. Create phone contact");
            System.out.println("3. Read email contact");
            System.out.println("4. Read phone contact");
            System.out.println("5. Update email contact");
            System.out.println("6. Update phone contact");
            System.out.println("7. Delete email contact");
            System.out.println("8. Delete phone contact");
            System.out.println("9. Filter email contact by first name");
            System.out.println("10. Filter phone contact by first name");
            System.out.println("11. Filter email contact by last name");
            System.out.println("12. Filter phone contact by last name");
            System.out.println("13. Filter email contact by First name and last name");
            System.out.println("14. Filter phone contact by First name and last name");
            System.out.println("15. Exit");

            var option = scanner.nextInt();

            try {
                switch (option) {
                    case 1 -> {
                        var emailContactDto = new EmailContactDto();
                        System.out.println("Enter first name:");
                        emailContactDto.setFirstName(scanner.next());
                        System.out.println("Enter last name:");
                        emailContactDto.setLastName(scanner.next());
                        System.out.println("Enter email:");
                        emailContactDto.setEmail(scanner.next());

                        var request = new Request(Request.RequestMethod.POST, "/email", emailContactDto);
                        client.SendRequest(request);
                    }
                    case 2 -> {
                        var phoneContactDto = new PhoneContactDto();
                        System.out.println("Enter first name:");
                        phoneContactDto.setFirstName(scanner.next());
                        System.out.println("Enter last name:");
                        phoneContactDto.setLastName(scanner.next());
                        System.out.println("Enter phone:");
                        phoneContactDto.setPhone(scanner.next());

                        var request = new Request(Request.RequestMethod.POST, "/phone", phoneContactDto);
                        client.SendRequest(request);
                    }
                    case 3 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/email/" + id, null);
                        client.SendRequest(request);
                    }
                    case 4 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/phone/" + id, null);
                        client.SendRequest(request);
                    }
                    case 5 -> {
                        var emailContactDto = new EmailContactDto();
                        System.out.println("Enter id:");
                        emailContactDto.setId(UUID.fromString(scanner.next()));
                        System.out.println("Enter first name:");
                        emailContactDto.setFirstName(scanner.next());
                        System.out.println("Enter last name:");
                        emailContactDto.setLastName(scanner.next());
                        System.out.println("Enter email:");
                        emailContactDto.setEmail(scanner.next());

                        var request = new Request(Request.RequestMethod.PUT, "/email", emailContactDto);
                        client.SendRequest(request);
                    }
                    case 6 -> {
                        var phoneContactDto = new PhoneContactDto();
                        System.out.println("Enter id:");
                        phoneContactDto.setId(UUID.fromString(scanner.next()));
                        System.out.println("Enter first name:");
                        phoneContactDto.setFirstName(scanner.next());
                        System.out.println("Enter last name:");
                        phoneContactDto.setLastName(scanner.next());
                        System.out.println("Enter phone:");
                        phoneContactDto.setPhone(scanner.next());

                        var request = new Request(Request.RequestMethod.PUT, "/phone", phoneContactDto);
                        client.SendRequest(request);
                    }
                    case 7 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();
                        var request = new Request(Request.RequestMethod.DELETE, "/email/" + id, null);
                        client.SendRequest(request);
                    }
                    case 8 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();
                        var request = new Request(Request.RequestMethod.DELETE, "/phone/" + id, null);
                        client.SendRequest(request);
                    }
                    case 9 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/email/byName/" + firstName, null);
                        client.SendRequest(request);
                    }
                    case 10 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/phone/byName/" + firstName, null);
                        client.SendRequest(request);
                    }
                    case 11 -> {
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/email/byLastName/" + lastName, null);
                        client.SendRequest(request);
                    }
                    case 12 -> {
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/phone/byLastName/" + lastName, null);
                        client.SendRequest(request);
                    }
                    case 13 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/email/byFullName/" + firstName + " " + lastName, null);
                        client.SendRequest(request);
                    }
                    case 14 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        var request = new Request(Request.RequestMethod.GET, "/phone/byFullName/" + firstName + " " + lastName, null);
                        client.SendRequest(request);
                    }
                    case 15 -> {
                        client.Stop();
                        System.exit(0);
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
