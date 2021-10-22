package com.cooksys.team4.services.impl;

import com.cooksys.team4.entities.User;
import com.cooksys.team4.exceptions.NotAuthorizedException;
import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.repositories.UserRepository;
import com.cooksys.team4.services.AuthService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    /**
     * FIXME: use constant time string comparison or bcrypt
     */
    public User authenticate(CredentialsDto credentialsDto) throws NotAuthorizedException {
        final var credentials = Optional.ofNullable(credentialsDto);
        final var username = credentials.map(CredentialsDto::getUsername).flatMap(Optional::ofNullable).orElse("");
        final var password = credentials.map(CredentialsDto::getPassword).flatMap(Optional::ofNullable).orElse("");
        return userRepository.findByCredentialsUsernameAndDeletedFalse(username)
            .filter(u -> u.getCredentials().getPassword().equals(password))
            .orElseThrow(() -> new NotAuthorizedException("Invalid User Credentials"));
    }

}
