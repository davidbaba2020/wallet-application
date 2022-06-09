package com.dacadave.walletapplication.exceptions;

public class UserWithEmailNotFound extends RuntimeException{

    public UserWithEmailNotFound(String message)  {
        super(message);
    }
}
