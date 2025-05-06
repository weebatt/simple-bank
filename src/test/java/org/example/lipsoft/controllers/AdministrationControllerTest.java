package org.example.lipsoft.controllers;

import org.example.lipsoft.DTO.BankAccountDTO;
import org.example.lipsoft.DTO.UserDTO;
import org.example.lipsoft.entities.Role;
import org.example.lipsoft.services.AdministrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AdministrationControllerTest {

    @Mock
    private AdministrationService administrationService;

    @InjectMocks
    private AdministrationController administrationController;

    @Test
    void registerUser_ShouldReturnSuccessMessage() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("john@example.com");
        userDTO.setPassword("password");
        userDTO.setRole(Role.CUSTOMER);

        String response = administrationController.registerUser(userDTO);

        verify(administrationService).registerUser(userDTO);
        assertEquals("User registered successfully", response);
    }

    @Test
    void registerAccount_ShouldReturnSuccessMessage() {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setUserId(1L);
        bankAccountDTO.setBalance(1000L);
        bankAccountDTO.setCurrency("USD");

        String response = administrationController.registerAccount(bankAccountDTO);

        verify(administrationService).registerBankAccount(bankAccountDTO);
        assertEquals("Account registered successfully", response);
    }
}