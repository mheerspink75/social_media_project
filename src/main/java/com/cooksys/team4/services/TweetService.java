package com.cooksys.team4.services;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.entities.Tweet;

public interface TweetService {
	
	public List<TweetResponseDto> getTweets();
	
	public TweetResponseDto postTweet(CredentialsDto credentialsDto, String content);
	
	public TweetResponseDto getTweetById(Long id);
	
	public TweetResponseDto deleteTweetById(Long id);
	
}
