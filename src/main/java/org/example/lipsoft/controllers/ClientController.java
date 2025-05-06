package org.example.lipsoft.controllers;

import org.example.lipsoft.DTO.BankAccountDTO;
import org.example.lipsoft.DTO.TransferDTO;
import org.example.lipsoft.services.ClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> getAllAccounts(@RequestParam("userId") Long userId) {
        return clientService.getAllUserBankAccounts(userId);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferDTO transferDTO) {
        clientService.transfer(transferDTO);
        return "Transfer successful";
    }
}
