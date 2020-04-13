package com.data.science.net.oauth.gateway;

import com.data.science.net.oauth.gateway.database.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserGateway {

    private final UserRepository userRepository;

    public UserDetails execute(final String username) {
        return userRepository.findByUsername(username).map(this::getUser).orElseThrow();
    }

    private org.springframework.security.core.userdetails.User getUser(final UserDetails userDetails) {
        return new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
