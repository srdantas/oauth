package com.data.science.net.oauth.usercase;

import com.data.science.net.oauth.gateway.FindUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("implementation")
public class UserDetailsUseCase implements UserDetailsService {

    private final FindUserGateway findUserGateway;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return findUserGateway.execute(username);
    }

}
