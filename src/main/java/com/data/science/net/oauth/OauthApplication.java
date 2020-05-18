package com.data.science.net.oauth;

import com.data.science.net.oauth.gateway.database.mongo.document.Rule;
import com.data.science.net.oauth.gateway.database.mongo.document.User;
import com.data.science.net.oauth.gateway.database.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static java.util.Collections.singletonList;

@SpringBootApplication
@RequiredArgsConstructor
public class OauthApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        final var user = User.builder()
                .username("renato.dantas")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .enable(Boolean.TRUE)
                .rules(singletonList(new Rule(UUID.randomUUID().toString(), "USER")))
                .build();
        userRepository.save(user);
    }
}
