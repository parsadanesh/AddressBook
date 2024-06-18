package com.dfcorp.application;

import com.dfcorp.app.AddressBook;
import com.dfcorp.app.Contact;
import com.dfcorp.app.Printer;
import com.dfcorp.app.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static boolean isRunning = true;

    AddressBook book = new AddressBook();
    Scanner scanInput = new Scanner(System.in);

    // Prints main menu
    private void printMainMenu() {
        System.out.println("=====================");
        System.out.println("1 - add a contact");
        System.out.println("2 - Search for a contact");
        System.out.println("3 - remove a contact");
        System.out.println("4 - edit a contact");
        System.out.println("5 - view all contacts");
        System.out.println("6 - delete all contacts");
        System.out.println("7 - quit");
        System.out.println("=====================");
    }

    // takes the users input for the main menu
    private String mainMenu() {
        String choice = "";
        printMainMenu();
        try {
            System.out.print("Enter: ");
            choice = scanInput.nextLine();
        } catch (Exception e) {
            System.out.println("Please enter a number");
        }
        return choice;
    }

    // Controls the logic behind the main menu
    private void menuController(){
        String choice = mainMenu();
        switch (choice){
            case "1":
                addContact();
                break;
            case "2":
                findContact();
                break;
            case "3":
                removeContact();
                break;
            case "4":
                editContact();
                break;
            case "5":
                viewAllContacts();
                break;
            case "6":
                deleteAllContacts();
                break;
            case "7":
                isRunning = false;
                goodBye();
                break;
            default:
                System.out.println("\nPlease Enter A Correct Value 1-6");
        }
    }

    // Method used to add contact to the address book
    private void addContact(){
        System.out.println("Add New Contact: ");
        String[] detailsToAdd = recieveContactDetails();
        try {
            Contact contactToAdd = new Contact(detailsToAdd[0], detailsToAdd[1], detailsToAdd[2]);
            book.addContact(contactToAdd);
        } catch (IllegalArgumentException e){
            System.out.println("Contact Not Added");
            System.out.println(e.getMessage());
        }
    }

    // Finds contacts that match a partial or complete phone number
    private void findContact(){
        System.out.println("Please choose the search type");
        System.out.println("1 - Name | 2 - Number | 3 - Email");
        System.out.println("Enter: ");
        String searchType = scanInput.nextLine();

        switch (searchType){
            case "1":
                findByName();
                break;
            case "2":
                findByNumber();
                break;
            case "3":
                findByEmail();
                break;
            default:
                System.out.println("Please enter a valid input (1, 2 or 3)");
        }
    }

    // Finds contacts that match a partial or complete name
    private void findByName(){
        System.out.println("Please enter the name of the contact you want to find:");
        String contactName = scanInput.nextLine();
        ArrayList<Contact> searchQuery = book.findContactByName(contactName);
        if(!searchQuery.isEmpty()) {
            System.out.println("Here is a list of contacts that match your search:");
            Printer.listAllContacts(searchQuery);
        }else{
            System.out.println("Not contacts by that name");
        }
    }

    // Finds contacts that match a partial or complete phone number
    private void findByNumber(){
        System.out.println("Please enter the number of the contact you want to find:");
        String contactNumber = scanInput.nextLine();
        ArrayList<Contact> searchQuery = book.findContactByNumber(contactNumber);
        if(!searchQuery.isEmpty()) {
            System.out.println("Here is a list of contacts that match your search:");
            Printer.listAllContacts(searchQuery);
        }else{
            System.out.println("No contacts with a number that matches");
        }
    }

    // Finds contacts that match a partial or complete phone number
    private void findByEmail(){
        System.out.println("Please enter the name of the contact you want to find:");
        String contactEmail = scanInput.nextLine();
        ArrayList<Contact> searchQuery = book.findContactByName(contactEmail);
        if(!searchQuery.isEmpty()) {
            System.out.println("Here is a list of contacts that match your search:");
            Printer.listAllContacts(searchQuery);
        }else{
            System.out.println("No contacts with an email that match");
        }
    }

    // Removed a contact from the address book
    private void removeContact(){
        System.out.println("Please enter the number of the contact you want to remove: ");
        String contactNumber = scanInput.next();
        if(findContactNumber(contactNumber) != null){
            Contact contactToRemove = findContactNumber(contactNumber);
            book.removeContact(contactToRemove);
        } else {
            System.out.println("There does not seem to be a contact with that number.");
        }

    }

    // method that is used to take in the contact details
    private String[] recieveContactDetails(){
        String[] details = new String[3];
        System.out.println("Name");
        String newName = scanInput.nextLine();
        System.out.println("Number");
        String newNumber = scanInput.nextLine();
        System.out.println("Email");
        String newEmail = scanInput.nextLine();
        details[0] = newName;
        details[1] = newNumber;
        details[2] = newEmail;
        return details;
    }

    // takes in new contact details and applies them to the contact
    private void editContact(){
        System.out.println("Please enter the number of the contact you want to edit: ");
        String contactNumber = scanInput.nextLine();
        if(findContactNumber(contactNumber) != null){
            System.out.println("Please enter the new details");
            String[] details = recieveContactDetails();
            try {
                book.editContact(details[0], details[1], details[2], findContactNumber(contactNumber));
            } catch (IllegalArgumentException e){
                System.out.println("Incorrect Details");
                System.out.println("Edit Unsuccessful");
            }
        }else{
            System.out.println("There does not seem to be a contact with that number.");
        }
    }

    // Prints goodbye message when user quits the applications
    private void goodBye(){
        System.out.println("Thank you for using DFCorp's Address Book.");
    }

    // Method used to find a contact by number
    private Contact findContactNumber(String number){
        for (Contact c: book.getContacts()){
            if(c.getNumber().equals(number)){
                return c;
            }
        }
        return null;
    }

    // Prints all the contacts in the address book using the Printer class
    private void viewAllContacts(){
        System.out.println("\nAll Contacts:");
        Printer.listAllContacts(book.getContacts());
    }

    // Confirms with user before deleing all the contacts
    private void deleteAllContacts(){
        System.out.println("Are you sure you would like to delete all your contacts");
        System.out.println("1 - Yes | 2 - No");
        String choice = scanInput.next();
        if (choice.equals("1")){
            book.deleteAllContacts(true);
        } else if (choice.equals("2")){
            book.deleteAllContacts(false);
        }else {
            System.out.println("Please enter a correct input (1 or 2)");
        }
    }

    // Where the application is run
    public static void main(String[] args) {
        App app = new App();
        while (isRunning) {
            app.menuController();
        }
    }

}
