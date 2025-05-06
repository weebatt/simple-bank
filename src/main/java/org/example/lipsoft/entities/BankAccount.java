package org.example.lipsoft.entities;

import lombok.Data;
import jakarta.persistence.*;

import java.util.UUID;

@Data
@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue
    private UUID accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private long balance;
    private String currency;
}
