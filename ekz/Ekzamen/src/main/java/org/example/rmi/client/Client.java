package org.example.rmi.client;

import org.example.dto.EmailContactDto;
import org.example.dto.PhoneContactDto;
import org.example.rmi.server.RMIServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        RMIServer server = (RMIServer) Naming.lookup("rmi://localhost:1099/server");

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
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        System.out.println("Enter email:");
                        var email = scanner.next();

                        EmailContactDto emailContactDto = new EmailContactDto(firstName, lastName, email);
                        emailContactDto = server.createEmailContact(emailContactDto);
                        System.out.println(emailContactDto);
                    }
                    case 2 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        System.out.println("Enter phone:");
                        var phone = scanner.next();

                        PhoneContactDto phoneContactDto = new PhoneContactDto(firstName, lastName, phone);
                        phoneContactDto = server.createPhoneContact(phoneContactDto);
                        System.out.println(phoneContactDto);
                    }
                    case 3 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();

                        EmailContactDto emailContactDto = server.readEmailContact(UUID.fromString(id));
                        System.out.println(emailContactDto);
                    }
                    case 4 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();

                        PhoneContactDto phoneContactDto = server.readPhoneContact(UUID.fromString(id));
                        System.out.println(phoneContactDto);
                    }
                    case 5 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        System.out.println("Enter email:");
                        var email = scanner.next();

                        EmailContactDto emailContactDto = new EmailContactDto(firstName, lastName, email);
                        emailContactDto.setId(UUID.fromString(id));
                        emailContactDto = server.updateEmailContact(emailContactDto);
                        System.out.println(emailContactDto);
                    }
                    case 6 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();
                        System.out.println("Enter phone:");
                        var phone = scanner.next();

                        PhoneContactDto phoneContactDto = new PhoneContactDto(firstName, lastName, phone);
                        phoneContactDto.setId(UUID.fromString(id));
                        phoneContactDto = server.updatePhoneContact(phoneContactDto);
                        System.out.println(phoneContactDto);
                    }
                    case 7 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();

                        server.deleteEmailContact(UUID.fromString(id));
                    }
                    case 8 -> {
                        System.out.println("Enter id:");
                        var id = scanner.next();

                        server.deletePhoneContact(UUID.fromString(id));
                    }
                    case 9 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();

                        var emailContactDtos = server.findAllEmailContactsByFirstName(firstName);
                        emailContactDtos.forEach(System.out::println);
                    }
                    case 10 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();

                        var phoneContactDtos = server.findAllPhoneContactsByFirstName(firstName);
                        phoneContactDtos.forEach(System.out::println);
                    }
                    case 11 -> {
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();

                        var emailContactDtos = server.findAllEmailContactsByLastName(lastName);
                        emailContactDtos.forEach(System.out::println);
                    }
                    case 12 -> {
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();

                        var phoneContactDtos = server.findAllPhoneContactsByLastName(lastName);
                        phoneContactDtos.forEach(System.out::println);
                    }
                    case 13 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();

                        var emailContactDtos = server.findAllEmailContactsByFullName(firstName, lastName);
                        emailContactDtos.forEach(System.out::println);
                    }
                    case 14 -> {
                        System.out.println("Enter first name:");
                        var firstName = scanner.next();
                        System.out.println("Enter last name:");
                        var lastName = scanner.next();

                        var phoneContactDtos = server.findAllPhoneContactsByFullName(firstName, lastName);
                        phoneContactDtos.forEach(System.out::println);
                    }
                    case 15 -> System.exit(0);
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
