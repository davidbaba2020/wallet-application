package com.dacadave.walletapplication.repository;

import com.dacadave.walletapplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role,Long> {
}
