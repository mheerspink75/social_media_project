package com.cooksys.team4.services.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.team4.dtos.ContextDto;
import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.entities.User;
import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.exceptions.BadRequestException;
import com.cooksys.team4.exceptions.NotAuthorizedException;
import com.cooksys.team4.exceptions.NotFoundException;
import com.cooksys.team4.mappers.TweetMapper;
import com.cooksys.team4.mappers.UserMapper;
import com.cooksys.team4.parsers.TweetParser;
import com.cooksys.team4.repositories.HashTagRepository;
import com.cooksys.team4.repositories.TweetRepository;
import com.cooksys.team4.repositories.UserRepository;
import com.cooksys.team4.services.AuthService;
import com.cooksys.team4.services.TweetService;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService{

	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;
	private final UserRepository userRepository;
	private final HashTagRepository hashtagRepository;
	private final TweetParser tweetParser;
	private final UserMapper userMapper;
	private final AuthService authService;

	@Override
	public List<TweetResponseDto> getTweets() {
		List<Tweet> tweetEntities = tweetRepository.findAllByDeletedOrderByPosted(false);
		return tweetMapper.entitiesToResponseDtos(tweetEntities);
	}

	/**
	 * FIXME: consider using constant-time string comparison for passwords
	 * FIXME: consider moving authorization outside this function
	 */
	@Transactional
	@Override
	public TweetResponseDto postTweet(CredentialsDto credentialsDto, String inputContent) {
		final var user = authService.authenticate(credentialsDto);
		final var content = Optional.ofNullable(inputContent).filter(s -> s.length() > 0).orElseThrow(() -> new BadRequestException("Empty content"));
		final var mentions = tweetParser.parseMentions(content).stream()
			.map(u -> userRepository.findByCredentialsUsernameAndDeletedFalse(u).orElseThrow(() -> new BadRequestException(u + " does not exist")))
			.collect(Collectors.toList());
		final var hashtags = tweetParser.parseHashtags(content).stream()
			.map(label -> hashtagRepository.findOneByLabelIgnoreCase(label).orElseGet(() -> {
				final var tag = new Hashtag();
				tag.setLabel(label);
				hashtagRepository.saveAndFlush(tag);
				return tag;
			}))
			.collect(Collectors.toList());
		final var tweet = new Tweet();
		tweet.setAuthor(user);
		tweet.setContent(content);
		tweet.setHashtags(hashtags);
		tweet.setUserMentioned(mentions);
		tweetRepository.saveAndFlush(tweet);
		return tweetMapper.entityToResponseDto(tweet);
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		Optional<Tweet> tweetEntity = tweetRepository.findById(id);
		if (!(tweetEntity.isPresent()) || tweetEntity.get().isDeleted()) {
			throw new NotFoundException("No tweet with such id was found");
		} 
		return tweetMapper.entityToResponseDto(tweetEntity.get());
	}

	/**
	 * FIXME: refactor authorization out
	 * FIXME: refactor User comparison
	 */
	@Override
	public TweetResponseDto deleteTweetById(CredentialsDto credentialsDto, long id) {
		final var user = authService.authenticate(credentialsDto);
		final var tweet = tweetRepository.findByIdAndDeletedFalse(id)
			.orElseThrow(() -> new BadRequestException("Tweet does not exist"));
		if (user.getId().longValue() != tweet.getAuthor().getId().longValue()) throw new NotAuthorizedException("User is not authorized");
		tweet.setDeleted(true);
		tweetRepository.saveAndFlush(tweet);
		return tweetMapper.entityToResponseDto(tweet);
	}

	@Override
	public void likeTweet(Long id, CredentialsDto credentialsDto) {
		final var user = authService.authenticate(credentialsDto);
		tweetRepository.findByIdAndDeletedFalse(id).ifPresentOrElse(tweet -> {
			tweet.getLikes().add(user);
			tweetRepository.save(tweet);
		}, () -> {throw new NotFoundException("Tweet not found");});  
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
		Optional<Tweet> tweetEntity = tweetRepository.findById(id);
		if(!(tweetEntity.isPresent() || tweetEntity.get().isDeleted())) {
			throw new NotFoundException("No tweet with such id was found");
		}
		List<User> usersAll = tweetEntity.get().getLikes();
		List<User> activeUsers = new ArrayList<>(); 
		for (User user : usersAll) {
			if (!user.isDeleted()) {
				activeUsers.add(user);
			}
		}
		return userMapper.entitiesToResponseDtos(activeUsers);
	}

	@Override
	public ContextDto getContext(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getReplies(Long id) {
		Optional<Tweet> tweetEntity = tweetRepository.findById(id);
		if(!(tweetEntity.isPresent() || tweetEntity.get().isDeleted())) {
			throw new NotFoundException("No tweet with such id was found");
		}
		List<Tweet> listTweets = tweetEntity.get().getReplies().stream().filter((t) -> !t.isDeleted()).collect(Collectors.toList());
		return tweetMapper.entitiesToResponseDtos(listTweets);
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