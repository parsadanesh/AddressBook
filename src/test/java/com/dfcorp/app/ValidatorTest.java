package com.dfcorp.app;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {

    @Nested
    class validateString {

        @Test
        void testWhenStringIsEmpty() {
            // Arrange
            String testString = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateString(testString));
        }

        @Test
        void testWhenContactStringIsNull() {
            // Arrange
            String testString = null;
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateString(testString));
        }
    }

    @Nested
    class validateNumber{
        @Test
        void testWhenNumberIsEmpty() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = "";
            String testEmail = "janedoe@dfmail.co.uk";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateNumber(testNumber));
        }

        @Test
        void testWhenNumberIsNull() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = null;
            String testEmail = "janedoe@dfmail.co.uk";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateNumber(testNumber));
        }

        @Test
        void throwsExceptionWhenIncorrectNumberFormat() {
            // Arrange
            String testName = "Jane Doe";
            String testNumber = "01234567890";
            String testEmail = "janedoe@dfmail.co.uk";
            //Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateNumber(testNumber));

        }
    }
}

