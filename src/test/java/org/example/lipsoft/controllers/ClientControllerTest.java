package org.example.lipsoft.controllers;

import org.example.lipsoft.DTO.BankAccountDTO;
import org.example.lipsoft.DTO.TransferDTO;
import org.example.lipsoft.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void getAllAccounts_ShouldReturnAccountList() {
        Long userId = 1L;
        BankAccountDTO accountDTO = new BankAccountDTO();
        accountDTO.setBalance(1000L);
        accountDTO.setCurrency("USD");
        List<BankAccountDTO> accounts = Arrays.asList(accountDTO);

        when(clientService.getAllUserBankAccounts(userId)).thenReturn(accounts);

        List<BankAccountDTO> result = clientController.getAllAccounts(userId);

        verify(clientService).getAllUserBankAccounts(userId);
        assertEquals(accounts, result);
    }

    @Test
    void transfer_ShouldReturnSuccessMessage() {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setFromAccountId(java.util.UUID.randomUUID());
        transferDTO.setToAccountId(java.util.UUID.randomUUID());
        transferDTO.setUserId(1L);
        transferDTO.setAmount(500L);

        String response = clientController.transfer(transferDTO);

        verify(clientService).transfer(transferDTO);
        assertEquals("Transfer successful", response);
    }
}