package org.example.lipsoft.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class TransferDTO {
    UUID fromAccountId;
    UUID toAccountId;
    Long userId;
    Long amount;
}
