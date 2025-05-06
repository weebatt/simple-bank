package org.example.lipsoft.repositories;

import jakarta.persistence.LockModeType;
import org.example.lipsoft.entities.BankAccount;
import org.example.lipsoft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BankAccount b WHERE b.accountNumber = :id")
    Optional<BankAccount> findByIdForUpdate(@Param("id") UUID id);
    List<BankAccount> findByUserId(Long userId);
}