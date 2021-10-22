package com.cooksys.team4.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.team4.dtos.ContentRequestDto;
import com.cooksys.team4.dtos.ContextDto;
import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.dtos.TweetRequestDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.UserRequestDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.services.TweetService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

	private final TweetService tweetService;
	
	/**
     * TODO: implement GET /tweets Retrieves all (non-deleted) tweets. The tweets
     * should appear in reverse-chronological order. Response: ['Tweet']
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TweetResponseDto> getTweets() {
    	return tweetService.getTweets();
    }

    /**
     * TODO: implement POST /tweets Creates a new simple tweet, with the author set
     * to the user identified by the credentials in the request body. If the given
     * credentials do not match an active user in the database, an error should be
     * sent in lieu of a response. The response should contain the newly-created
     * tweet. Because this always creates a simple tweet, it must have a content
     * property and may not have inReplyTo or repostOf properties. IMPORTANT: when a
     * tweet with content is created, the server must process the tweet's content
     * for @{username} mentions and #{hashtag} tags. There is no way to create
     * hashtags or create mentions from the API, so this must be handled
     * automatically! Request: {content: 'string', credentials: 'Credentials'}
     * Response: 'Tweet'
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto dto) {
        return tweetService.postTweet(dto.getCredentials(), dto.getContent());
    }

    /**
     * TODO: implement GET /tweets/{id} Retrieves a tweet with a given id. If no
     * such tweet exists, or the given tweet is deleted, an error should be sent in
     * lieu of a response. Response: 'Tweet'
     */
   
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public TweetResponseDto getTweet(@PathVariable long id) {
    	return tweetService.getTweetById(id);
    }

    /**
     * TODO: implement DELETE /tweets/{id} "Deletes" the tweet with the given id. If
     * no such tweet exists or the provided credentials do not match author of the
     * tweet, an error should be sent in lieu of a response. If a tweet is
     * successfully "deleted", the response should contain the tweet data prior to
     * deletion. IMPORTANT: This action should not actually drop any records from
     * the database! Instead, develop a way to keep track of "deleted" tweets so
     * that even if a tweet is deleted, data with relationships to it (like replies
     * and reposts) are still intact. Request: 'Credentials' Response: 'Tweet'
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweet(@RequestBody CredentialsDto credentialsDto, @PathVariable long id) {
        return tweetService.deleteTweetById(credentialsDto, id);
    }

    /**
     * TODO: implement POST /tweets/{id}/like Creates a "like" relationship between
     * the tweet with the given id and the user whose credentials are provided by
     * the request body. If the tweet is deleted or otherwise doesn't exist, or if
     * the given credentials do not match an active user in the database, an error
     * should be sent. Following successful completion of the operation, no response
     * body is sent. Request: 'Credentials'
     */
    @PostMapping("/{id}/like")
    public void addLike(@PathVariable long id, @RequestBody CredentialsDto credentialsDto) {
    	tweetService.likeTweet(id, credentialsDto);
    }

    /**
     * TODO: implement POST /tweets/{id}/reply Creates a reply tweet to the tweet
     * with the given id. The author of the newly-created tweet should match the
     * credentials provided by the request body. If the given tweet is deleted or
     * otherwise doesn't exist, or if the given credentials do not match an active
     * user in the database, an error should be sent in lieu of a response. Because
     * this creates a reply tweet, content is not optional. Additionally, notice
     * that the inReplyTo property is not provided by the request. The server must
     * create that relationship. The response should contain the newly-created
     * tweet. IMPORTANT: when a tweet with content is created, the server must
     * process the tweet's content for @{username} mentions and #{hashtag} tags.
     * There is no way to create hashtags or create mentions from the API, so this
     * must be handled automatically! Request: {content: 'string', credentials:
     * 'Credentials'} Response: 'Tweet'
     */
    @PostMapping("/{id}/reply")
    public TweetResponseDto addReply(@PathVariable long id, 
    		@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.replyTweet(id, tweetRequestDto.getCredentials(), 
				tweetRequestDto.getContent());
    }

    /**
     * TODO: implement POST /tweets/{id}/repost Creates a repost of the tweet with
     * the given id. The author of the repost should match the credentials provided
     * in the request body. If the given tweet is deleted or otherwise doesn't
     * exist, or the given credentials do not match an active user in the database,
     * an error should be sent in lieu of a response. Because this creates a repost
     * tweet, content is not allowed. Additionally, notice that the repostOf
     * property is not provided by the request. The server must create that
     * relationship. The response should contain the newly-created tweet. Request:
     * 'Credentials' Response: 'Tweet'
     */
    @PostMapping("/{id}/repost")
    public void addRepost(@PathVariable long id) {
    }

    /**
     * TODO: implement GET /tweets/{id}/tags Retrieves the tags associated with the
     * tweet with the given id. If that tweet is deleted or otherwise doesn't exist,
     * an error should be sent in lieu of a response. IMPORTANT Remember that tags
     * and mentions must be parsed by the server! Response: ['Hashtag']
     */
    @GetMapping("/{id}/tags")
    public List<HashTagDto> getTags(@PathVariable long id) {
    	return tweetService.getHashTags(id);
    }

    /**
     * TODO: implement GET /tweets/{id}/likes Retrieves the active users who have
     * liked the tweet with the given id. If that tweet is deleted or otherwise
     * doesn't exist, an error should be sent in lieu of a response. Deleted users
     * should be excluded from the response. Response: ['User']
     */
    @GetMapping("/{id}/likes")
    public List<UserResponseDto> getLikes(@PathVariable long id) {
    	return tweetService.getLikes(id);
    }

    /**
     * TODO: implement GET /tweets/{id}/context Retrieves the context of the tweet
     * with the given id. If that tweet is deleted or otherwise doesn't exist, an
     * error should be sent in lieu of a response. IMPORTANT: While deleted tweets
     * should not be included in the before and after properties of the result,
     * transitive replies should. What that means is that if a reply to the target
     * of the context is deleted, but there's another reply to the deleted reply,
     * the deleted reply should be excluded but the other reply should remain.
     * Response: 'Context'
     */
    @GetMapping("/{id}/context")
    public ContextDto getContext(@PathVariable long id) {
    	return tweetService.getContext(id);
    }

    /**
     * TODO: implement GET /tweets/{id}/replies Retrieves the direct replies to the
     * tweet with the given id. If that tweet is deleted or otherwise doesn't exist,
     * an error should be sent in lieu of a response. Deleted replies to the tweet
     * should be excluded from the response. Response: ['Tweet']
     */
    @GetMapping("/{id}/replies")
    public List<TweetResponseDto> getReplies(@PathVariable long id) {
    	return tweetService.getReplies(id);
    }

    /**
     * TODO: implement GET /tweets/{id}/reposts Retrieves the direct reposts of the
     * tweet with the given id. If that tweet is deleted or otherwise doesn't exist,
     * an error should be sent in lieu of a response. Deleted reposts of the tweet
     * should be excluded from the response. Response: ['Tweet']
     */
    @GetMapping("/{id}/reposts")
    public void getReposts(@PathVariable long id) {
    }

    /**
     * TODO: implement GET /tweets/{id}/mentions Retrieves the users mentioned in
     * the tweet with the given id. If that tweet is deleted or otherwise doesn't
     * exist, an error should be sent in lieu of a response. Deleted users should be
     * excluded from the response. IMPORTANT Remember that tags and mentions must be
     * parsed by the server! Response: ['User']
     */
    @GetMapping("/{id}/mentions")
    public void getMentions(@PathVariable long id) {
    }
}