package com.cooksys.team4.services;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.entities.User;
import com.cooksys.team4.exceptions.NotAuthorizedException;

public interface AuthService {
    /**
     * Returns a user or throws a NotAuthorized Exception
     * @param credentials
     * @return User
     */
    public User authenticate(CredentialsDto credentialsDto) throws NotAuthorizedException;
}
