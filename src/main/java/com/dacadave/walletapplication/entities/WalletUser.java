package com.dacadave.walletapplication.entities;

import com.dacadave.walletapplication.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet_user")
public class WalletUser extends BaseClass{
    @Size(min = 2, max =30)
    @NotBlank(message ="Firstname can't be blank")
    private String firstName;

    @Size(min = 2, max =30)
    @NotBlank(message ="Lastname can't be blank")
    private String lastName;

    @Email(message="Please, enter a valid email")
    @NotBlank(message ="email can't be blank")
    private String email;

    @NotBlank(message ="Password is necessary")
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Role> roles = new HashSet<>();

    private boolean isAccountVerified;
}
