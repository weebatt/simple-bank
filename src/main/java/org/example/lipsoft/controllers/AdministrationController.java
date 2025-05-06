package org.example.lipsoft.controllers;

import org.example.lipsoft.DTO.BankAccountDTO;
import org.example.lipsoft.DTO.UserDTO;
import org.example.lipsoft.services.AdministrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/register")
public class AdministrationController {
    private final AdministrationService administrationService;

    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @PostMapping("/user")
    public String registerUser(@RequestBody UserDTO userDTO) {
        administrationService.registerUser(userDTO);
        return "User registered successfully";
    }

    @PostMapping("/account")
    public String registerAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        administrationService.registerBankAccount(bankAccountDTO);
        return "Account registered successfully";
    }
}
