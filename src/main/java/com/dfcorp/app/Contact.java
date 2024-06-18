package com.dfcorp.app;


public class Contact {

    private String name;
    private String number;
    private String email;



    public Contact(String name, String number, String email) {
        if (Validator.validateString(name)){this.name = name;}
        if (Validator.validateNumber(number)){this.number = number;}
        if (Validator.validateEmail(email)) {this.email = email;}
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        if(Validator.validateString(name)){this.name = name;}
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number){
        if(Validator.validateNumber(number)){this.number = number;}
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        if(Validator.validateEmail(email)){
            this.email = email;
        }
    }




}
