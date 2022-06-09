package com.dacadave.walletapplication.entities;


import com.dacadave.walletapplication.enums.TransactionType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseClass{
    private Long walletId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String dateAndTimeForTransaction;

    private Double transactionAmount;

    @Size(max = 100)
    private String Summary;

    private LocalDateTime transactionDate;
}
