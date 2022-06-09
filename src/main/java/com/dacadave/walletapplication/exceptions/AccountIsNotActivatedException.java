package com.dacadave.walletapplication.exceptions;

public class AccountIsNotActivatedException extends RuntimeException {
    public AccountIsNotActivatedException(String message) {
        super(message);
    }
}
