package com.cooksys.tVeam4.services;

import com.cooksys.team4.dtos.TweetResponseDto;

import java.util.List;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.entities.Tweet;

public interface TweetService {
	
	public List<TweetResponseDto> getTweets();
	
	public TweetResponseDto postTweet(CredentialsDto credentialsDto, String content);
	
	public TweetResponseDto getTweetById(Long id);
	
	public TweetResponseDto deleteTweetById(Long id);
	
}
