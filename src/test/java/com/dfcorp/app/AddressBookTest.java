package com.dfcorp.app;

import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {
    Contact mockContact = mock(Contact.class);
    Contact mockContact2 = mock(Contact.class);
    Contact mockContact3 = mock(Contact.class);
    Contact mockContact4 = mock(Contact.class);
    Contact mockContact5 = mock(Contact.class);
    Contact mockContact6 = mock(Contact.class);
    Contact copyContact = mock(Contact.class);
    Contact copyContact2 = mock(Contact.class);
    AddressBook testBook;

    @BeforeEach
    public void setUp(){
        testBook = new AddressBook();

        when(mockContact.getName()).thenReturn("jamie");
        when(mockContact.getNumber()).thenReturn("07123456789");
        when(mockContact.getEmail()).thenReturn("test@email.co.uk");


        when(mockContact2.getName()).thenReturn("daniel");
        when(mockContact2.getNumber()).thenReturn("07123465585");
        when(mockContact2.getEmail()).thenReturn("test2@email.co.uk");

        when(mockContact3.getName()).thenReturn("jane");
        when(mockContact3.getNumber()).thenReturn("07321634787");
        when(mockContact3.getEmail()).thenReturn("test3@email.co.uk");

        when(mockContact4.getName()).thenReturn("david");
        when(mockContact4.getNumber()).thenReturn("07123444883");
        when(mockContact4.getEmail()).thenReturn("test4@email.co.uk");

        when(mockContact5.getName()).thenReturn("jamie");
        when(mockContact5.getNumber()).thenReturn("07412345768");
        when(mockContact5.getEmail()).thenReturn("test5@email.co.uk");

        when(mockContact6.getName()).thenReturn("yusef");
        when(mockContact6.getNumber()).thenReturn("07213666782");
        when(mockContact6.getEmail()).thenReturn("test6@email.co.uk");

        // Same email as mock6
        when(copyContact.getName()).thenReturn("mike");
        when(copyContact.getNumber()).thenReturn("07566096712");
        when(copyContact.getEmail()).thenReturn("test6@email.co.uk");

        // same number as mock6
        when(copyContact2.getName()).thenReturn("hannah");
        when(copyContact2.getNumber()).thenReturn("07213666782");
        when(copyContact2.getEmail()).thenReturn("test7@email.co.uk");
    }

    @AfterEach
    public void tearDown(){
        testBook = null;
    }

    @DisplayName("Adding A Contact")
    @Nested
    class AddContactTests {
        @Test
        void addNewContactCheckArraySize() {
            // Arrange
            int expected = testBook.getContacts().size() + 1;
            Contact mockContact = mock(Contact.class);
            // Act
            testBook.addContact(mockContact);
            int actual = testBook.getContacts().size();
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        void addNewContactCheckStoreInArray() {
            // Arrange
            boolean expected = true;
            // Act
            testBook.addContact(mockContact);
            boolean actual = testBook.getContacts().contains(mockContact);
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        void checkAddingNullContactThrowsExpection() {
            // Arrange
            Contact testContact = null;
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testBook.addContact(testContact));
        }

    }

    // added new edge cases for email and number that return all contacts with a partial matching email or number
    @DisplayName("Searching for Contacts")
    @Nested
    class searchForContacts {

        @DisplayName("Searching for Contacts by name")
        @Nested
        class searchByName {
            @Test
            void checkContactsHave() {
                testBook.addContact(mockContact);
                // Act
                ArrayList<Contact> actual = testBook.findContactByName("jamie");
                // Assert
                assertTrue(actual.contains(mockContact));
            }

            @Test
            void checkAllContactsFound() {
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                testBook.addContact(mockContact3);
                testBook.addContact(mockContact5);
                // Act
                ArrayList<Contact> actual = testBook.findContactByName("jamie");
                // Assert
                assertEquals(actual.size(), 2);
            }

            @Test
            void testWhenThereAreNoContacts() {
                // Arrange
                // Act
                ArrayList<Contact> actual = testBook.findContactByName("jamie");
                // Assert
                assertEquals(actual.size(), 0);
            }

            @Test
            void findNullContact() {
                // Arrange
                Contact testContact = null;
                // Act
                ArrayList<Contact> testArr = testBook.findContactByName(null);
                int actual = testArr.size();
                // Assert
                assertEquals(0, actual);
            }

            @Test
            void findAllWithPartialName() {
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                testBook.addContact(mockContact3);
                testBook.addContact(mockContact4);
                testBook.addContact(mockContact5);
                // Act
                ArrayList<Contact> testArr = testBook.findContactByName("an");
                int actual = testArr.size();
                // Assert
                assertEquals(2, actual);
            }

            @Test
            void findAllWithPartialNameCheck() {
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2); // Daniel
                testBook.addContact(mockContact3); // Jane
                testBook.addContact(mockContact4);
                testBook.addContact(mockContact5);

                // Act
                ArrayList<Contact> testArr = testBook.findContactByName("an");

                // Assert
                assertAll(
                        () -> assertTrue(testArr.contains(mockContact2)),
                        () -> assertTrue(testArr.contains(mockContact3))
                );
            }

        }

        @DisplayName("Searching for Contacts by number")
        @Nested
        class searchByNumber{
            @Test
            void testFindContactByNumber(){
                // Arrange
                testBook.addContact(mockContact); //07123456789
                testBook.addContact(mockContact2);
                // Act
                ArrayList<Contact> contactFound = testBook.findContactByNumber("07123456789");
                // Assert
                assertEquals(contactFound.get(0), mockContact);
            }


            @Test
            void testFindContactWithWrongNumber(){
                // Arrange
                testBook.addContact(mockContact); //07123456789
                testBook.addContact(mockContact2);
                // Act
                ArrayList<Contact> contactFound = testBook.findContactByNumber("07123323789");
                // Assert
                assertEquals(contactFound.size(), 0);
            }

            @Test
            void testSearchWithNullNumber(){
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                // Act
                ArrayList<Contact> contactFound = testBook.findContactByNumber(null);
                // Assert
                assertEquals(contactFound.size(), 0);
            }

            @Test
            void testSearchWithPartialNumber(){
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                testBook.addContact(mockContact3);
                // Act
                ArrayList<Contact> contactFound = testBook.findContactByNumber("071");
                // Assert
                assertEquals(contactFound.size(), 2);
            }
        }

        @DisplayName("Searching for Contacts by email")
        @Nested
        class searchByEmail{

            @Test
            void testFindContactByEmail(){
                // Arrange
                testBook.addContact(mockContact); //07123456789
                testBook.addContact(mockContact2);
                // Act
                ArrayList<Contact> contactsFound = testBook.findContactByEmail("test@email.co.uk");
                // Assert
                assertEquals(contactsFound.get(0), mockContact);
            }

            @Test
            void testFindContactsByEmail(){
                // Arrange
                testBook.addContact(mockContact); //07123456789
                testBook.addContact(mockContact2);
                testBook.addContact(mockContact3);
                // Act
                ArrayList<Contact> contactFound = testBook.findContactByEmail("est");

                // Assert
                assertEquals(contactFound.size(), 3);
            }

            @Test
            void testFindContactWithWrongEmail(){
                // Arrange
                testBook.addContact(mockContact); //07123456789
                testBook.addContact(mockContact2);
                // Act
                ArrayList<Contact> contactsFound = testBook.findContactByEmail("wrong@email.co.uk");
                // Assert
                assertEquals(contactsFound.size(), 0);
            }

            @Test
            void testSearchWithNullEmail(){
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                // Act
                ArrayList<Contact> contactFound = testBook.findContactByEmail(null);
                // Assert
                assertEquals(contactFound.size(), 0);
            }

            @Test
            void testSameEmailDifferentCaseSensitivity() {
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                testBook.addContact(mockContact3);
                testBook.addContact(mockContact5);
                // Act
                ArrayList<Contact> actual = testBook.findContactByEmail("tESt@email.co.uk");
                // Assert
                assertEquals(actual.size(), 1);
            }
        }
    }

    @Nested
    class removeContacts {

        @Test
        void removeAContactCheckArraySizeDecreases() {
            // Arrange
            testBook.addContact(mockContact);
            testBook.addContact(mockContact2);
            testBook.addContact(mockContact3);
            int expected = testBook.getContacts().size() - 1;
            // Act
            testBook.removeContact(mockContact2);
            int actual = testBook.getContacts().size();
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        void checkContactIsRemoved() {
            // Arrange
            testBook.addContact(mockContact);
            testBook.addContact(mockContact2);
            testBook.addContact(mockContact3);
            boolean expected = !(testBook.getContacts().contains(mockContact2));
            // Act
            testBook.removeContact(mockContact2);
            boolean actual = testBook.getContacts().contains(mockContact2);

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        void removeAnInvalidContact() {
            // Arrange
            testBook.addContact(mockContact);
            testBook.addContact(mockContact2);
            int expected = testBook.getContacts().size();
            // Act
            testBook.removeContact(mockContact3);
            int actual = testBook.getContacts().size();
            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    class editContact {

        @Test
        void editContactNumberCheckNewValueStored(){
            String expected = "07543256334";
            Contact testContact = new Contact("jamie", "07123456789", "test@email.co.uk");
            testBook.addContact(testContact);

            testBook.editContact("jamie", "07543256334","test@email.co.uk", testContact);

            assertEquals(testContact.getNumber(), expected);

        }


        @Test
        void editContactEmailCheckNewValueStored(){
            Contact testContact = new Contact("jamie", "07123456789", "test@email.co.uk");
            String expected = "testEdit@email.co.uk";
            testBook.addContact(testContact);
            // Act
            testBook.editContact("jamie","07123456789","testEdit@email.co.uk", testContact);
            // Assert
            assertEquals(expected, testContact.getEmail());
        }


        @Test
        void editContactWithANullNumber(){
            Contact testContact = new Contact("jamie", "07123456789", "test@email.co.uk");
            testBook.addContact(testContact);
            assertThrows(IllegalArgumentException.class, () -> testBook.editContact("jamie",null,"test@email.co.uk", testContact));

        }

        @Test
        void editContactWithEmptyNumber(){
            Contact testContact = new Contact("jamie", "07123456789", "test@email.co.uk");
            testBook.addContact(testContact);
            assertThrows(IllegalArgumentException.class, () -> testBook.editContact("jamie","","test@email.co.uk", testContact));

        }
    }

    @Nested
    class checkForDuplicates {
        @Test
        void addContactWithSameNumberTwiceCheckArraySize(){
            testBook.addContact(mockContact6);
            int expected = testBook.getContacts().size();

            testBook.addContact(copyContact2);
            int actual = testBook.getContacts().size();

            assertEquals(expected, actual);
        }

        @Test
        void addContactWithSameEmailTwiceCheckArraySize(){
            // Arrange
            testBook.addContact(mockContact6);
            int expected = testBook.getContacts().size();
            // Act
            testBook.addContact(copyContact);
            int actual = testBook.getContacts().size();
            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    class alphabeticalSearch {
        @BeforeEach
        public void populate() {
            testBook.addContact(mockContact); // jamie (3)
            testBook.addContact(mockContact2); //daniel (1)
            testBook.addContact(mockContact3); // jane (5)
            testBook.addContact(mockContact4); // david (2)
            testBook.addContact(mockContact5); // jamie (3)

        }

        @Test
        void testFindWithPartialNameAlphabeticaly() {
            ArrayList<Contact> contacts = testBook.findContactByName("an");

            assertAll(
                    () -> assertEquals(2, contacts.size()),
                    () -> assertEquals(contacts.get(0), mockContact2),
                    () -> assertEquals(contacts.get(1), mockContact3)
            );
        }

        @Test
        void testSortingWhenSearchByNumber() {
            ArrayList<Contact> contacts = testBook.findContactByNumber("071");
            assertAll(
                    () -> assertEquals(3, contacts.size()),
                    () -> assertEquals(contacts.get(0), mockContact2),
                    () -> assertEquals(contacts.get(1), mockContact4),
                    () -> assertEquals(contacts.get(2), mockContact)
            );
        }

        @Test
        void testSortingWhenSearchByEmail() {
            ArrayList<Contact> contacts = testBook.findContactByEmail("test");
            assertAll(
                    () -> assertEquals(5, contacts.size()),
                    () -> assertEquals(contacts.get(0), mockContact2),
                    () -> assertEquals(contacts.get(1), mockContact4),
                    () -> assertEquals(contacts.get(2), mockContact),
                    () -> assertEquals(contacts.get(3), mockContact5),
                    () -> assertEquals(contacts.get(4), mockContact3)
            );
        }
    }

    @Nested
    class deleteAllContact {
        @Nested
        class conformationTests {
            @Test
            void testDeleteConfirmation() {
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                // Act
                testBook.deleteAllContacts(true);
                // Assert
                assertTrue(testBook.getContacts().isEmpty());
            }

            @Test
            void testDeleteCancelation() {
                // Arrange
                testBook.addContact(mockContact);
                testBook.addContact(mockContact2);
                // Act
                testBook.deleteAllContacts(false);
                // Assert
                assertEquals(testBook.getContacts().size(), 2);
            }
        }

    }
}
