package com.dacadave.walletapplication.entities;

import com.dacadave.walletapplication.enums.ProductCatigory;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table
@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseClass{
    @NotBlank(message ="Product name can't be blank")
    private String productName;

    private Double productAmount;

    private ProductCatigory catigory;
}
