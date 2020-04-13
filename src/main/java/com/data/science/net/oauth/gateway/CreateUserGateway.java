package com.data.science.net.oauth.gateway;

import com.data.science.net.oauth.gateway.database.mongo.document.User;
import com.data.science.net.oauth.gateway.database.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserGateway {

    private final UserRepository repository;

    public void execute(final User user) {
        this.repository.save(user);
    }
}
