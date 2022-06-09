package com.dacadave.walletapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountActivationDto {
    private String code;
    private String email;
}
