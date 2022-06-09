package com.dacadave.walletapplication.service;

import com.dacadave.walletapplication.dto.AccountActivationDto;
import com.dacadave.walletapplication.dto.PinChangeDto;
import com.dacadave.walletapplication.dto.WalletUserDto;

public interface WalletUserInterface {
    String signUpAsAUser(WalletUserDto walletUser);
    String accountActivation(AccountActivationDto accountActivation);
    String changeTransactionPin(PinChangeDto pinChangeDto);
}
