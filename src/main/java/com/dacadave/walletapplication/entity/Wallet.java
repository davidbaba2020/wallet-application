package com.dacadave.walletapplication.entity;


import com.dacadave.walletapplication.enums.WalletType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


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
    private Double amount;
    private Integer walletAccountNumber;
    @Enumerated(EnumType.STRING)
    private WalletType walletType;
    private String transactionPin;
    private String accountHolderEmail;
}
