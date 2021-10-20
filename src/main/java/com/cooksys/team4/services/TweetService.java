package com.cooksys.team4.services;

import com.cooksys.team4.dtos.TweetResponseDto;

import java.util.List;

public interface TweetService {
    List<TweetResponseDto> getAllTweets();
}
