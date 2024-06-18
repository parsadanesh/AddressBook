package com.dfcorp.app;

import java.util.ArrayList;
import java.util.Comparator;

public class AddressBook {
    // FIELDS
    private ArrayList<Contact> contacts = new ArrayList<>();

    // PRIVATE METHODS
    private void validateContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("Null Contact");
        }
    }

    // METHODS
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    // Returns false if duplicate is found
    public boolean checkForDuplicates(Contact contactToAdd){
        for (Contact c : contacts){
            if(c.getNumber().equals(contactToAdd.getNumber()) || c.getEmail().equals(contactToAdd.getEmail())){
                return false;
            }
        }
        return true;
    }

    // Adds a contact to the Address Book
    public void addContact(Contact newContact) {
        validateContact(newContact);
        if(checkForDuplicates(newContact)){
            contacts.add(newContact);
        }else {
            System.out.println("Could not add, duplicate: " + newContact.getName());
        }

    }

    // Takes a name and returns contacts with said name
    public ArrayList<Contact> findContactByName(String searchedName){
        ArrayList<Contact> searchArr = new ArrayList<>();
        for(Contact contact: contacts){
            if(searchedName != null && contact.getName().contains(searchedName)){
                searchArr.add((contact));
            }
        }
        sortContactsByName(searchArr);
        return searchArr;
    }

    // Returns a contact with a matching number or null
    public ArrayList<Contact> findContactByNumber(String searchNumber){
        ArrayList<Contact> searchArr = new ArrayList<>();
        for(Contact contact: contacts){
            if(searchNumber != null && contact.getNumber().contains(searchNumber)){
                searchArr.add(contact);
            }
        }
        sortContactsByName(searchArr);
        return searchArr;
    }

    // Returns a contact with a matching email or null
    public ArrayList<Contact> findContactByEmail(String searchEmail){
        ArrayList<Contact> searchArr = new ArrayList<>();
        for(Contact contact: contacts){
            if(searchEmail != null && contact.getEmail().toLowerCase().contains(searchEmail.toLowerCase())){
               searchArr.add(contact);
            }
        }
        sortContactsByName(searchArr);
        return searchArr;
    }

    // Sorts the contact list alphabetically
    public void sortContactsByName(ArrayList<Contact> contactsToSort) {
        contactsToSort.sort(Comparator.comparing(Contact::getName));
    }

    // Removes a contact from the Address Book
    public void removeContact(Contact contactToRemove){
        if(contacts.contains(contactToRemove)){
            this.contacts.remove(contactToRemove);
            System.out.println(contactToRemove.getName() + " has been removed");
        }else {
            System.out.println("Contact is not in the address book.");
        }

    }

    // Adds new values for the customer details
    public void editContact(String name, String number, String email, Contact contactToEdit){
        contactToEdit.setName(name);
        contactToEdit.setNumber(number);
        contactToEdit.setEmail(email);
    }

    // Takes in a boolean and if true deletes all the contacts from the address book
    public void deleteAllContacts(boolean choice){
        if(choice) {
            contacts.clear();
            System.out.println("All contacts have been deleted");
        }else{
            System.out.println("Deletion cancelled, Contacts remain unchanged.");
        }
    }


}
