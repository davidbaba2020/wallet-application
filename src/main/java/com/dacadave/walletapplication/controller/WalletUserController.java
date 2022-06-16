package com.dacadave.walletapplication.controller;

import com.dacadave.walletapplication.dto.AccountActivationDto;
import com.dacadave.walletapplication.dto.PinChangeDto;
import com.dacadave.walletapplication.dto.RoleDto;
import com.dacadave.walletapplication.dto.WalletUserDto;
import com.dacadave.walletapplication.responses.HttpResponse;
import com.dacadave.walletapplication.responses.ResponseData;
import com.dacadave.walletapplication.service.ServiceImpl.WalletUserServiceImpl;
import com.dacadave.walletapplication.utils.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value ="/wallet")
@RequiredArgsConstructor
public class WalletUserController {
    private final WalletUserServiceImpl walletUserService;
    private final ValidationService validationService;

    @PostMapping("sign-up")
    public ResponseEntity<?> createAccount (@Valid @RequestBody WalletUserDto walletUserDto, BindingResult result)
    {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        return new ResponseEntity<String>(walletUserService.signUpAsAUser(walletUserDto),HttpStatus.CREATED);
    }


    @PutMapping("/account-activation")
    public ResponseEntity<ResponseData> verifyAndActivateAccount(@RequestBody AccountActivationDto accountActivation)
    {
        return response(OK,walletUserService.accountActivation(accountActivation));
    }


    @PutMapping("/transaction-pin-change")
    public ResponseEntity<ResponseData> changePin (@RequestBody PinChangeDto pinChangeDto)
    {
        return response(OK,walletUserService.changeTransactionPin(pinChangeDto));
    }

    @PostMapping(value = "/create-role")
    public ResponseEntity<ResponseData> createRole(@RequestBody RoleDto roleDto)
    {
        return response(OK, walletUserService.saveRole(roleDto));
    }


    public static ResponseEntity<ResponseData> response(HttpStatus httpStatus, String message)
    {
        return new  ResponseEntity<>(new ResponseData(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message.toUpperCase())), httpStatus );
    }
}
