package com.dacadave.walletapplication.entity;

import lombok.*;

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
public class Role extends BaseClass {
    private String name;
}
