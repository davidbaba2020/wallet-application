package com.dacadave.walletapplication.entities;


import com.dacadave.walletapplication.enums.WalletType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Table
@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseClass{

    private Long walletOwnerId;

    private Double currentBalance;

    private String walletAccountNumber;

    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    @Size(min = 4, max =4)
    private String transactionPin;

    private String accountHolderEmail;
}
