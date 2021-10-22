package com.cooksys.team4.controllers;

import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.services.HashTagService;
import com.cooksys.team4.services.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tags")

public class HashtagController {

    private HashTagService hashTagService;
    //private TweetService tweetService;

    @GetMapping
    public List<HashTagDto> getAllHashtags() {
        return hashTagService.getAllHashtags();
    }

    @GetMapping("/{label}")
    public void getTagsLabel(@PathVariable String label) {
    }

    /**
     * TODO: implement Get /{label} Retrieves all (non-deleted) tweets tagged with
     * the given hashtag label. The tweets should appear in reverse-chronological
     * order. If no hashtag with the given label exists, an error should be sent in
     * lieu of a response. A tweet is considered "tagged" by a hashtag if the tweet
     * has content and the hashtag's label appears in that content following a #
     * Response: 'Hashtag'
     */

}
