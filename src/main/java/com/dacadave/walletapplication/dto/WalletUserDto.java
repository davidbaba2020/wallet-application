package com.dacadave.walletapplication.dto;

import com.dacadave.walletapplication.enums.Gender;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletUserDto {

        @Size(min = 2, max =30)
        @NotBlank(message ="Firstname can't be blank")
        private String firstName;

        @Size(min = 2, max =30)
        @NotBlank(message ="Lastname can't be blank")
        private String lastName;

        @NotBlank(message ="email can't be blank")
        private String email;

        @NotBlank(message ="Password is necessary")
        private String password;

        @Enumerated(EnumType.STRING)
        private Gender gender;

}
