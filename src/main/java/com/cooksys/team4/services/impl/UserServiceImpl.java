package com.cooksys.team4.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cooksys.team4.dtos.*;
import com.cooksys.team4.entities.Profile;
import com.cooksys.team4.entities.User;
import com.cooksys.team4.exceptions.BadRequestException;
import com.cooksys.team4.exceptions.NotAuthorizedException;
import com.cooksys.team4.exceptions.NotFoundException;
import com.cooksys.team4.mappers.UserMapper;
import com.cooksys.team4.repositories.UserRepository;
import com.cooksys.team4.services.UserService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private void validateUserRequest(UserRequestDto userRequestDto)  {

        if (userRequestDto.getCredentials() == null || userRequestDto.getCredentials().getUsername() == null || userRequestDto.getCredentials().getPassword() == null || userRequestDto.getProfile() == null || userRequestDto.getProfile().getEmail() == null) {
            throw new BadRequestException("All fields are required");
        }
    }

    private boolean validateCredentialsFromRequest(String username,
                                          UserRequestDto userRequestDto) {

        Optional<User> optionalUser =
                userRepository.findByCredentialsUsername(username);

        return optionalUser.isPresent() && checkIfUserExistsNotDeleted(username) && userRequestDto.getCredentials().getPassword().equals(optionalUser.get().getCredentials().getPassword()) && userRequestDto.getCredentials().getUsername().equals(optionalUser.get().getCredentials().getUsername());
    }

    private boolean checkIfUserExists(String username) {

        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);

        if (optionalUser.isPresent()) {
            throw new BadRequestException("A user with username : " + username
                    + " already exists");
        }

        return false;
    }

    private boolean checkIfUserExistsNotDeleted(String username) {

        if (checkIfUserExists(username) && !userRepository.findByCredentialsUsernameAndDeletedFalse(username).get().isDeleted()) {
            return true;
        }
        throw new BadRequestException("User does not exist or is deleted");
    }

    private User handleUserExists(User user) {
        if (user.isDeleted()) {
            user.setDeleted(false);
            userRepository.saveAndFlush(user);
            return user;
        }
        throw new BadRequestException("User exists and is active");
    }

    private User handleNotUserExists(User userToSave) {
        userRepository.saveAndFlush(userToSave);
        return userToSave;
    }

    /**
     * TODO: implement Retrieves all active (non-deleted) users as an array.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----users">...</a>
     */
    @Override
    public List<UserResponseDto> getAllUsers() {

        return userMapper.entitiesToResponseDtos(userRepository.findAllByDeletedFalse());
    }

    /**
     * TODO: implement Creates a new user. If any required fields are missing or the
     * username provided is already taken, an error should be sent in lieu of a
     * response. If the given credentials match a previously-deleted user,
     * re-activate the deleted user instead of creating a new one.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#post----users">...</a>
     */
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        validateUserRequest(userRequestDto);
        String username = userRequestDto.getCredentials().getUsername();

        User userToSave = userMapper.requestDtoToEntity(userRequestDto);
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
        User user =
                optionalUser.map(this::handleUserExists).orElseGet(() -> handleNotUserExists(userToSave));

        return userMapper.entityToResponseDto(user);
    }

    /**
     * TODO: implement Retrieves a user with the given username. If no such user
     * exists or is deleted, an error should be sent in lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusername">...</a>
     */
    @Override
    public UserResponseDto getUser(String username) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);

        User existingUser = optionalUser.orElseThrow(() -> new NotFoundException(
                "User doesn't exist"));

        if (existingUser.isDeleted()) {
            throw new NotFoundException("Unable to get user");
        }

        return userMapper.entityToResponseDto(existingUser);

    }

    /**
     * TODO: implement Updates the profile of a user with the given username.
     * If no such user exists, the user is deleted, or the provided credentials do not
     * match the user, an error should be sent in lieu of a response. In the case of
     * a successful update, the returned user should contain the updated data.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#patch---usersusername">...</a>
     */
    @Override
    public UserResponseDto updateUserProfile(String username, UserRequestDto userRequestDto) {

        Optional<User> optionalUser =
                userRepository.findByCredentialsUsernameAndDeletedFalse(username);

        User userToUpdate = optionalUser.orElseThrow(() -> new NotFoundException(
                "User doesn't exist"));

        if (userToUpdate.getCredentials().getPassword().equals(userRequestDto.getCredentials().getPassword()) && userToUpdate.getCredentials().getUsername().equals(userRequestDto.getCredentials().getUsername())) {

            ProfileDto profileDto = userRequestDto.getProfile();
            Profile newProfile = new Profile();
            newProfile.setFirstName(profileDto.getFirstName());
            newProfile.setLastName(profileDto.getLastName());
            newProfile.setEmail(profileDto.getEmail());
            newProfile.setPhone(profileDto.getPhone());

            userToUpdate.setProfile(newProfile);

            userRepository.saveAndFlush(userToUpdate);

            return userMapper.entityToResponseDto(userToUpdate);
        }

        throw new NotAuthorizedException("Username or password do not match");
    }

    /**
     * TODO: implement "Deletes" a user with the given username.
     * If no such user exists or the provided credentials do not match the user, an
     * error should be
     * sent in lieu of a response. If a user is successfully "deleted", the response
     * should contain the user data prior to deletion. IMPORTANT: This action should
     * not actually drop any records from the database! Instead, develop a way to
     * keep track of "deleted" users so that if a user is re-activated, all of their
     * tweets and information are restored.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#delete--usersusername">...</a>
     *
     * TODO: Handle a missing credential property
     */
    @Override
    public UserResponseDto deleteUser(String username, CredentialsDto credentials)  {
        Optional<User> optionalUser =
                Optional.ofNullable(userRepository.findByCredentialsUsernameAndDeletedFalse(username).orElseThrow(() -> new NotFoundException(
                        "User Not Found")));

        User userToDelete = optionalUser.orElseThrow(() -> new NotFoundException(
                "User doesn't exist"));

        userToDelete.setDeleted(true);
        return userMapper.entityToResponseDto(userRepository.saveAndFlush(userToDelete));

    }

    /**
     * TODO Subscribes the user whose credentialsDto are provided by the request body
     * to the user whose username is given in the url. If there is already a
     * following relationship between the two users, no such followable user
     * exists (deleted or never created), or the credentialsDto provided do not
     * match an active user in the database, an error should be sent as a response.
     *
     * If successful, no data is sent.
     *
     *  credentialsDto to follow username
     *
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#post----usersusernamefollow">...</a>
     */
    @Override
    public void followUser(String followerUsername, CredentialsDto credentialsDto) {

        final var credentials = Optional.ofNullable(credentialsDto);

        final var username =
                credentials.map(CredentialsDto::getUsername).flatMap(Optional::ofNullable).orElse("");

        final var password = credentials.map(CredentialsDto::getPassword).flatMap(Optional::ofNullable).orElse("");

        // Follower
        final var user =
                userRepository.findByCredentialsUsernameAndDeletedFalse(username)
                        .filter(u -> u.getCredentials().getPassword().equals(password))
                        .orElseThrow(() -> new NotAuthorizedException("Username and " +
                                "Password need to be provided"));

        // User to follow
        final var follower =
                userRepository.findByCredentialsUsernameAndDeletedFalse(followerUsername).orElseThrow(() -> new NotFoundException("User To Follow Not Found"));

        // Check if already follower
        if (!follower.getFollowers().contains(user)) {

            follower.getFollowers().add(user);


            userRepository.saveAndFlush(follower);

        }

    }


    /**
     * TODO: implement Unsubscribes the user whose credentials are provided by the
     * request body from the user whose username is given in the url. If there is no
     * preexisting following relationship between the two users, no such followable
     * user exists (deleted or never created), or the credentials provided do not
     * match an active user in the database, an error should be sent as a response.
     * If successful, no data is sent.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#post----usersusernameunfollow">...</a>
     */
    @Override
    public void unfollowUser(String username, CredentialsDto credentials) {
    }

    /**
     * TODO: implement Retrieves all (non-deleted) tweets authored by the user with
     * the given username, as well as all (non-deleted) tweets authored by users the
     * given user is following. This includes simple tweets, reposts, and replies.
     * The tweets should appear in reverse-chronological order. If no active user
     * with that username exists (deleted or never created), an error should be sent
     * in lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernamefeed">...</a>
     */
    @Override
    public List<TweetResponseDto> getFeed(String username) {
        return List.of();
    }

    /**
     * TODO: implement Retrieves all (non-deleted) tweets authored by the user with
     * the given username. This includes simple tweets, reposts, and replies. The
     * tweets should appear in reverse-chronological order. If no active user with
     * that username exists (deleted or never created), an error should be sent in
     * lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernametweets">...</a>
     */
    @Override
    public List<TweetResponseDto> getTweets(String username) {
        return List.of();
    }

    /**
     * TODO: implement Retrieves all (non-deleted) tweets in which the user with the
     * given username is mentioned. The tweets should appear in
     * reverse-chronological order. If no active user with that username exists, an
     * error should be sent in lieu of a response. A user is considered "mentioned"
     * by a tweet if the tweet has content and the user's username appears in that
     * content following a @.
     */
    @Override
    public List<TweetResponseDto> getMentions(String username) {
        return List.of();
    }

    /**
     * TODO: implement Retrieves the followers of the user with the given username.
     * Only active users should be included in the response. If no active user with
     * the given username exists, an error should be sent in lieu of a response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernamefollowers">...</a>
     */
    @Override
    public List<UserResponseDto> getFollowers(String username) {
        return List.of();
    }

    /**
     * TODO: implement Retrieves the users followed by the user with the given
     * username. Only active users should be included in the response. If no active
     * user with the given username exists, an error should be sent in lieu of a
     * response.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get-----usersusernamefollowing">...</a>
     */
    @Override
    public List<UserResponseDto> getFollowing(String username) {
        return List.of();
    }
}
