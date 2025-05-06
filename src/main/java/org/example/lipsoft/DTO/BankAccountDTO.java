package org.example.lipsoft.DTO;

import lombok.Data;

@Data
public class BankAccountDTO {
    long balance;
    String currency;
    long userId;
}
