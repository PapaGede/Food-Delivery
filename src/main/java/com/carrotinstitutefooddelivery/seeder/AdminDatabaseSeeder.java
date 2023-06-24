package com.carrotinstitutefooddelivery.seeder;

import com.carrotinstitutefooddelivery.constant.UserType;
import com.carrotinstitutefooddelivery.exceptions.DuplicateEmailException;
import com.carrotinstitutefooddelivery.model.User;
import com.carrotinstitutefooddelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AdminDatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var defaultUser = User.builder()
                .userId(UUID.randomUUID())
                .userType(UserType.ADMIN)
                .email("user@email.com")
                .password(passwordEncoder.encode("password"))
                .userName("admin")
                .token(UUID.randomUUID().toString())
                .build();
        if (userRepository.findUserByEmail(defaultUser.getEmail()).isPresent()) {
            return;
        }
        userRepository.save(defaultUser);

    }
}
