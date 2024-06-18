package com.dfcorp.app;

import java.util.ArrayList;

public class Printer {

    public static void listAllContacts(ArrayList<Contact> contacts){
        for(Contact contact: contacts){
            System.out.println("Name: " + contact.getName() + "\nNumber: " + contact.getNumber() + "\nEmail: " + contact.getEmail() + "\n");
        }
    }
}
