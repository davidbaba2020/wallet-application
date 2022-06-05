package com.dacadave.walletapplication.entity;

import com.dacadave.walletapplication.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet_user")
public class User extends BaseClass{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    private boolean isAccountVerified;
}
