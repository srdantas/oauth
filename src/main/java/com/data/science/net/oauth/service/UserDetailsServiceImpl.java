package com.data.science.net.oauth.service;

import com.data.science.net.oauth.gateway.database.mongo.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("implementation")
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).map(this::getUser).orElseThrow();
  }

  private User getUser(final UserDetails userDetails) {
    return new User(
        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
  }
}
