package com.cooksys.team4.services.impl;

import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.mappers.TweetMapper;
import com.cooksys.team4.repositories.TweetRepository;
import com.cooksys.team4.services.TweetService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {
        List<Tweet> notDeletedAndOrdered = tweetRepository.findAllByDeletedFalse();
                notDeletedAndOrdered.sort((tweet1, tweet2) -> tweet2.getPosted().compareTo(tweet1.getPosted()));
        return tweetMapper.entitiesToResponseDtos(notDeletedAndOrdered);
    }

}
