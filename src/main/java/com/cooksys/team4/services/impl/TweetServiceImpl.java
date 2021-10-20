package com.cooksys.team4.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.team4.dtos.ContextDto;
import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.exceptions.NotFoundException;
import com.cooksys.team4.mappers.TweetMapper;
import com.cooksys.team4.repositories.TweetRepository;
import com.cooksys.team4.services.TweetService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService{

	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;


	@Override
	public List<TweetResponseDto> getTweets() {
		List<Tweet> tweetEntities = tweetRepository.findAllByDeletedOrderByPosted(false);
		return tweetMapper.entitiesToResponseDtos(tweetEntities);
	}

	@Override
	public TweetResponseDto postTweet(CredentialsDto credentialsDto, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		Optional<Tweet> tweetEntity = tweetRepository.findById(id);
		if (!(tweetEntity.isPresent()) || tweetEntity.get().isDeleted()) {
			throw new NotFoundException("No tweet with such id was found");
		} 
		return tweetMapper.entityToResponseDto(tweetEntity.get());
	}

	@Override
	public TweetResponseDto deleteTweetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void likeTweet(Long id, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public TweetResponseDto replyTweet(Long id, CredentialsDto credentialsDto, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto repostTweet(Long id, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashTagDto> getHashTags(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getLikes(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContextDto getContext(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getReplies(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getReposts(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getMentions(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}