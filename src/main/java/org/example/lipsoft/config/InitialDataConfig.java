package org.example.lipsoft.config;

import org.example.lipsoft.entities.Role;
import org.example.lipsoft.entities.User;
import org.example.lipsoft.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDataConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${default.admin.name}")
    private String name;
    @Value("${default.admin.email}")
    private String email;
    @Value("${default.admin.password}")
    private String password;
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (userRepository.findByRole(Role.ADMIN).isEmpty()) {
                User admin = new User();
                admin.setName(name);
                admin.setEmail(email);
                admin.setPassword(passwordEncoder.encode(password));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Default admin user created.");
            }
        };
    }
}