package org.example;

import org.example.model.PhoneContact;

public class Main {
    public static void main(String[] args) {

        var contact = new PhoneContact("John", "Doe", "123456789");
        var str = contact.toString();
        System.out.println(str);
    }
}