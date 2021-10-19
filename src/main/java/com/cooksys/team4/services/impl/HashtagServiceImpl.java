package com.cooksys.team4.services.impl;

import java.util.List;

import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.services.HashTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashTagService {
    
    @Override
    public List<HashTagDto> getAllHashtags() {
        return null;
    }

    @Override
    public List<Tweet> getTweetsByHashtagLabel(Hashtag label) {
        return null;
    }
}
