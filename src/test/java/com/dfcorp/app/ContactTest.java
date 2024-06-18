package com.dfcorp.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @DisplayName("Adding A Contact")
    @Nested
    class CreatingContactTests {
        @DisplayName("Set Constructor With Valid Values")
        @Test
        void testConstructorSetsValuesWhenValid() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = "07874757126";
            String testEmail = "janedoe@dfmail.co.uk";
            // Act
            Contact testContact = new Contact(testName, testNumber, testEmail);
            // Assert
            assertAll("Constructor sets values when valid",
                    () -> assertEquals(testName, testContact.getName()),
                    () -> assertEquals(testNumber, testContact.getNumber()),
                    () -> assertEquals(testEmail, testContact.getEmail())
            );
        }

        @Test
        void testConstructorThrowsExceptionWithEmptyName() {
            // Arrange
            String testName = "";
            String testNumber = "07874757126";
            String testEmail = "janedoe@dfmail.co.uk";
            //Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testNumber, testEmail));

        }

        @Test
        void testConstructorThrowsExceptionWithNullName() {
            // Arrange
            String testName = null;
            String testNumber = "07874757126";
            String testEmail = "janedoe@dfmail.co.uk";
            //Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testNumber, testEmail));

        }


        @Test
        void testConstructorThrowsExceptionWithNullNumber() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = null;
            String testEmail = "janedoe@dfmail.co.uk";
            //Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testNumber, testEmail));

        }

        @Test
        void testConstructorThrowsExceptionWrongNumberFormat() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = "01234567890";
            String testEmail = "janedoe@dfmail.co.uk";
            //Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testNumber, testEmail));
        }

        @Test
        void testConstructorThrowsExceptionWrongEmailFormat() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = "01234567890";
            String testEmail = "janedoeemail.co.uk";
            //Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testNumber, testEmail));
        }


    }
}
