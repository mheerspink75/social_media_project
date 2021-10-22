package com.cooksys.team4.services;

import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.UserResponseDto;

import java.util.List;

import com.cooksys.team4.dtos.ContextDto;
import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.entities.Tweet;

public interface TweetService {
	
	public List<TweetResponseDto> getTweets();
	
	public TweetResponseDto postTweet(CredentialsDto credentialsDto, String content);
	
	public TweetResponseDto getTweetById(Long id);
	
	public TweetResponseDto deleteTweetById(CredentialsDto credentialsDto, long id);
	
	public void likeTweet(Long id, CredentialsDto credentialsDto);
	
	public TweetResponseDto replyTweet(Long id, CredentialsDto credentialsDto, String content);
	
	public TweetResponseDto repostTweet(long id, CredentialsDto credentialsDto);
	
	public List<HashTagDto> getHashTags(Long id);
	
	public List<UserResponseDto> getLikes(Long id);
	
	public ContextDto getContext(Long id);
	
	public List<TweetResponseDto> getReplies(Long id);
	
	public List<TweetResponseDto> getReposts(Long id);
	
	public List<UserResponseDto> getMentions(Long id); 
	
	
	
}
