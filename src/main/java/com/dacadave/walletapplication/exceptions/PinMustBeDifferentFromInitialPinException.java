package com.dacadave.walletapplication.exceptions;

public class PinMustBeDifferentFromInitialPinException extends RuntimeException {
    public PinMustBeDifferentFromInitialPinException(String message) {
        super(message);
    }
}
