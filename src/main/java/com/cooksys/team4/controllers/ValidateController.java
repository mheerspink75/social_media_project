package com.cooksys.team4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")

public class ValidateController {

    @GetMapping("/username/exists/@{username}")
    public void userNameExists(@PathVariable String username) {
        /**
         * TODO: implement Get /username/exists/@{username} Checks whether or not a
         * given username exists. Response: 'boolean'
         */
    }

    @GetMapping("/username/available/@{username}")
    public void userNameAvailable(@PathVariable String username) {

        /**
         * TODO: implement Get /username/available/@{username} Checks whether or not a
         * given username is available. Response: 'boolean'
         */
    }

    @GetMapping("/tag/exists/@{label}")
    public void tagExists(@PathVariable String label) {
        /**
         * TODO: implement Get /tag/exists/@{label} Checks whether or not a given
         * hashtag exists. Response: 'boolean'
         */
    }
}
