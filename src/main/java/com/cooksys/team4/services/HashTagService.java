package com.cooksys.team4.services;

import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.entities.Tweet;

import java.util.List;

public interface HashTagService {

    /**
     * Retrieves all hashtags tracked by the database
     */
    public List<HashTagDto> getAllHashtags();

    /**
     * Retrieves all (non-deleted) tweets tagged with the given hashtag label. The tweets should appear in reverse-chronological order. If no hashtag with the given label exists, an error should be sent in lieu of a response.
       A tweet is considered "tagged" by a hashtag if the tweet has content and the hashtag's label appears in that content following a #
     */
    public List<Tweet> getTweetsByHashtagLabel(Hashtag label);

}
