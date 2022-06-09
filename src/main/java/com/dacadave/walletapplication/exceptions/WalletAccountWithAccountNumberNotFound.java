package com.dacadave.walletapplication.exceptions;

public class WalletAccountWithAccountNumberNotFound extends RuntimeException{

    public WalletAccountWithAccountNumberNotFound(String message)  {
        super(message);
    }
}
