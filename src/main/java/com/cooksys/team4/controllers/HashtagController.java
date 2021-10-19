package com.cooksys.team4.controllers;


import java.util.List;

import com.cooksys.team4.entities.User;
import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.services.HashTagService;
import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.dtos.ErrorDto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.UserRequestDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.User;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")

public class HashtagController {

    @GetMapping
    public void getTags() {

        /**
         * TODO: implement
         * Get /tags
         * Response: 'Hashtag'
         */
    }

    @GetMapping("/{label}")
    public void getTagsLabel(@PathVariable String label) {}

    /**
     * TODO: implement
     * Get /{label}
     * Retrieves all (non-deleted) tweets tagged with the given hashtag label.
     * The tweets should appear in reverse-chronological order.
     * If no hashtag with the given label exists, an error should be sent in lieu of a response.
     * A tweet is considered "tagged" by a hashtag if the tweet has content and the hashtag's label appears in that content following a #
     * Response: 'Hashtag'
     */

}
