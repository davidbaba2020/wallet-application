package com.dacadave.walletapplication.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseClass {
    private String name;
}
