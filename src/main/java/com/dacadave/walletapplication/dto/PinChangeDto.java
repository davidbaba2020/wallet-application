package com.dacadave.walletapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinChangeDto
{
    private String walletOwnerEmail;
    private String newPin;
}
