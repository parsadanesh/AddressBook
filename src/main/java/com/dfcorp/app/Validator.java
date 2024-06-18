package com.dfcorp.app;

import java.util.regex.Pattern;

public class Validator {


    // Checks if string is null or empty
    public static boolean validateString(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Input was empty");
        }
        return true;
    }

    // Checks that number is in the correct format
    public static boolean validateNumber(String number){
        if(validateString(number) && Pattern.matches("^07\\d{9}$",number)){
            return true;
        }else{
            throw new IllegalArgumentException("Invalid Number format must be 07-followed by 9 number");
        }
    }

    // Checks that email is in the correct format
    public static boolean validateEmail(String email){
        if(validateString(email) && email.matches("^[a-zA-Z0-9._%+-]+(?:@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}|[._a-zA-Z0-9+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$")){
            return true;
        }else {
            throw new IllegalArgumentException("Invalid Email format");
        }
    }
}
