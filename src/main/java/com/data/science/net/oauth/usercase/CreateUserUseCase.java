package com.data.science.net.oauth.usercase;

import com.data.science.net.oauth.gateway.CreateUserGateway;
import com.data.science.net.oauth.gateway.database.mongo.document.Rule;
import com.data.science.net.oauth.gateway.database.mongo.document.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final CreateUserGateway createUserGateway;

    public void execute(final String username, final String password) {
        final var user = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .enable(Boolean.TRUE)
                .rules(singletonList(new Rule(UUID.randomUUID().toString(), "USER")))
                .build();

        createUserGateway.execute(user);
    }
}
