package com.docsbymario.my.config.security.service;

import com.docsbymario.my.entity.User;
import com.docsbymario.my.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username must be provided.");
        }

        List<User> userList = userRepository.findByUsername(username);

        if (userList.size() == 0) {
            userList = userRepository.findByEmail(username);
            if (userList.size() == 0) {
                throw new UsernameNotFoundException(
                        "Account not found for " + username + ".");
            }
        }

        User user = userList.get(0);

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).build();
    }
}
