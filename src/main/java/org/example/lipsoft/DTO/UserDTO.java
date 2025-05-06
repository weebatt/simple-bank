package org.example.lipsoft.DTO;

import lombok.Data;
import org.example.lipsoft.entities.Role;

@Data
public class UserDTO {
    String name;
    String email;
    String password;
    Role role;
}
