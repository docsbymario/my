package com.docsbymario.my.service;

import com.docsbymario.my.dto.RegisterDto;
import com.docsbymario.my.entity.User;
import com.docsbymario.my.exception.impl.user.EmailAlreadyExistsException;
import com.docsbymario.my.exception.impl.user.PasswordDoesNotSatisfyConstraintsException;
import com.docsbymario.my.exception.impl.user.PasswordsDoNotMatchException;
import com.docsbymario.my.repository.UserRepository;
import com.docsbymario.my.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordValidator passwordValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterDto registerDto) throws EmailAlreadyExistsException, PasswordsDoNotMatchException, PasswordDoesNotSatisfyConstraintsException {
        if (userRepository.findByUsername(registerDto.getUsername()).size() > 0) {
            throw new EmailAlreadyExistsException();
        }

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        if (!passwordValidator.validate(registerDto.getPassword())) {
            throw new PasswordDoesNotSatisfyConstraintsException();
        }

        User user = new User(registerDto.getEmail(), registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()));
        userRepository.insert(user);
    }
}