package com.dacadave.walletapplication.repository;

import com.dacadave.walletapplication.entities.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletUserRepository extends JpaRepository <WalletUser, Long> {

    Optional<WalletUser> findUserByEmail(String email);
}
