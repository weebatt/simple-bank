package org.example.lipsoft.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.lipsoft.DTO.BankAccountDTO;
import org.example.lipsoft.DTO.UserDTO;
import org.example.lipsoft.entities.BankAccount;
import org.example.lipsoft.entities.User;
import org.example.lipsoft.repositories.BankAccountRepository;
import org.example.lipsoft.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    UserRepository userRepository;
    BankAccountRepository bankAccountRepository;
    PasswordEncoder passwordEncoder;

    public AdministrationService(UserRepository userRepository,
                                 BankAccountRepository bankAccountRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(UserDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
    }

    @Transactional
    public void registerBankAccount(BankAccountDTO bankAccount) {
        User owner = userRepository.findById(bankAccount.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + bankAccount.getUserId()));

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setUser(owner);
        newBankAccount.setBalance(bankAccount.getBalance());
        newBankAccount.setCurrency(bankAccount.getCurrency());
        bankAccountRepository.save(newBankAccount);
    }
}
