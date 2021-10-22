package com.cooksys.team4.controllers;

import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.services.UserService;
import com.cooksys.team4.services.ValidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")

public class ValidateController {

    private final ValidateService validateService;

    @GetMapping("/username/exists/@{username}")
    public boolean userNameExists(@PathVariable String username) {
        /**
         * TODO: implement Get /username/exists/@{username} Checks whether or not a
         * given username exists. Response: 'boolean'
         */
        return validateService.usernameExists(username);
    }

    @GetMapping("/username/available/@{username}")
    public boolean userNameAvailable(@PathVariable String username) {

        /**
         * TODO: implement Get /username/available/@{username} Checks whether or not a
         * given username is available. Response: 'boolean'
         */
        return validateService.usernameAvailable(username);
    }

    @GetMapping("/tag/exists/{label}")
    public boolean tagExists(@PathVariable String label) {
        /**
         * TODO: implement Get /tag/exists/@{label} Checks whether or not a given
         * hashtag exists. Response: 'boolean'
         */
        return validateService.hashtagExists(label);
    }
}
