package com.cooksys.team4.services;

import java.util.List;
import java.util.Optional;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.UserRequestDto;
import com.cooksys.team4.dtos.UserResponseDto;

public interface UserService {
    /**
     * Retrieves all active (non-deleted) users as an array.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----users">...</a>
     */
    List<UserResponseDto> getAllUsers();

    /**
     * Creates a new user. If any required fields are missing or the username
     * provided is already taken, an error should be sent in lieu of a response. If
     * the given credentials match a previously-deleted user, re-activate the
     * deleted user instead of creating a new one.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#post----users">...</a>
     */
    UserResponseDto createUser(UserRequestDto user);

    /**
     * Retrieves a user with the given username. If no such user exists or is
     * deleted, an error should be sent in lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusername">...</a>
     */
    UserResponseDto getUser(String username);

    /**
     * Updates the profile of a user with the given username. If no such user
     * exists, the user is deleted, or the provided credentials do not match the
     * user, an error should be sent in lieu of a response. In the case of a
     * successful update, the returned user should contain the updated data.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#patch---usersusername">...</a>
     */
    UserResponseDto updateUserProfile(String username, UserRequestDto userRequestDto);

    /**
     * "Deletes" a user with the given username. If no such user exists or the
     * provided credentials do not match the user, an error should be sent in lieu
     * of a response. If a user is successfully "deleted", the response should
     * contain the user data prior to deletion. IMPORTANT: This action should not
     * actually drop any records from the database! Instead, develop a way to keep
     * track of "deleted" users so that if a user is re-activated, all of their
     * tweets and information are restored.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#delete--usersusername">...</a>
     */
    UserResponseDto deleteUser(String username, CredentialsDto credentials);

    /**
     * Subscribes the user whose credentials are provided by the request body to the
     * user whose username is given in the url. If there is already a following
     * relationship between the two users, no such followable user exists (deleted
     * or never created), or the credentials provided do not match an active user in
     * the database, an error should be sent as a response. If successful, no data
     * is sent.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#post----usersusernamefollow">...</a>
     */
    void followUser(String username, CredentialsDto credentials);

    /**
     * Unsubscribes the user whose credentials are provided by the request body from
     * the user whose username is given in the url. If there is no preexisting
     * following relationship between the two users, no such followable user exists
     * (deleted or never created), or the credentials provided do not match an
     * active user in the database, an error should be sent as a response. If
     * successful, no data is sent.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#post----usersusernameunfollow">...</a>
     */
    public void unfollowUser(String username, CredentialsDto credentials);

    /**
     * Retrieves all (non-deleted) tweets authored by the user with the given
     * username, as well as all (non-deleted) tweets authored by users the given
     * user is following. This includes simple tweets, reposts, and replies. The
     * tweets should appear in reverse-chronological order. If no active user with
     * that username exists (deleted or never created), an error should be sent in
     * lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernamefeed">...</a>
     */
    public List<TweetResponseDto> getFeed(String username);

    /**
     * Retrieves all (non-deleted) tweets authored by the user with the given
     * username. This includes simple tweets, reposts, and replies. The tweets
     * should appear in reverse-chronological order. If no active user with that
     * username exists (deleted or never created), an error should be sent in lieu
     * of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernametweets">...</a>
     */
    public List<TweetResponseDto> getTweets(String username);

    /**
     * Retrieves all (non-deleted) tweets in which the user with the given username
     * is mentioned. The tweets should appear in reverse-chronological order. If no
     * active user with that username exists, an error should be sent in lieu of a
     * response. A user is considered "mentioned" by a tweet if the tweet has
     * content and the user's username appears in that content following a @.
     */
    public List<TweetResponseDto> getMentions(String username);

    /**
     * Retrieves the followers of the user with the given username. Only active
     * users should be included in the response. If no active user with the given
     * username exists, an error should be sent in lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernamefollowers">...</a>
     */
    public List<UserResponseDto> getFollowers(String username);

    /**
     * Retrieves the users followed by the user with the given username. Only active
     * users should be included in the response. If no active user with the given
     * username exists, an error should be sent in lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernamefollowing">...</a>
     */
    public List<UserResponseDto> getFollowing(String username);

}
