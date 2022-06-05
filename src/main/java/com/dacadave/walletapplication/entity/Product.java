package com.dacadave.walletapplication.entity;

import lombok.*;
import org.hibernate.annotations.Tables;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseClass{
    private String productName;
    private Double productAmount;
    private ProductCatigory catigory;
}
