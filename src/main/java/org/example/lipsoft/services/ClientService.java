package org.example.lipsoft.services;

import jakarta.transaction.Transactional;
import org.example.lipsoft.DTO.BankAccountDTO;
import org.example.lipsoft.DTO.TransferDTO;
import org.example.lipsoft.entities.BankAccount;
import org.example.lipsoft.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    BankAccountRepository bankAccountRepository;

    public ClientService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public List<BankAccountDTO> getAllUserBankAccounts(Long userId) {
        List<BankAccount> accounts = bankAccountRepository.findByUserId(userId);
        return accounts.stream()
                .map(this::toBankAccountDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void transfer(TransferDTO transferDTO) {
        UUID fromAccountId = transferDTO.getFromAccountId();
        UUID toAccountId = transferDTO.getToAccountId();
        Long userId = transferDTO.getUserId();
        Long amount = transferDTO.getAmount();

        BankAccount from = bankAccountRepository.findByIdForUpdate(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        BankAccount to = bankAccountRepository.findByIdForUpdate(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Target account not found"));

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (!(from.getUser().getId() == userId) ||
                !(to.getUser().getId() == userId)) {
            throw new SecurityException("Accounts do not belong to user " + userId);
        }

        if (!from.getCurrency().equals(to.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        if (from.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        bankAccountRepository.save(from);
        bankAccountRepository.save(to);
    }

    private BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setBalance(bankAccount.getBalance());
        bankAccountDTO.setCurrency(bankAccount.getCurrency());
        return bankAccountDTO;
    }
}
