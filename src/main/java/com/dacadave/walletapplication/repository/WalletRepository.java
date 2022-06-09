package com.dacadave.walletapplication.repository;

import com.dacadave.walletapplication.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository <Wallet, Long> {
    Optional<Wallet> findWalletByWalletAccountNumber(String accountNumber);
    Optional<Wallet> findByAccountHolderEmail(String email);
}
