package com.cooksys.team4.controllers;

import java.util.List;

import com.cooksys.team4.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.UserRequestDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/@{username}")
    public UserResponseDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping("/@{username}")
    public UserResponseDto patchUser(@PathVariable String username,
                                     @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUserProfile(username, userRequestDto);
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        return userService.deleteUser(username, credentialsDto);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/@{username}/follow")
    public void addFollower(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
        userService.followUser(username, credentialsDto);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/@{username}/unfollow")
    public void removeFollower(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
        userService.unfollowUser(username, credentialsDto);

    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto> getFeed(@PathVariable String username) {
        return null;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto> getTweets(@PathVariable String username) {
        return null;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto> getMentions(@PathVariable String username) {
        return null;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("@{username}/followers")
    public List<UserResponseDto> getFollowers(@PathVariable String username) {
        return userService.getFollowers(username);

    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("@{username}/following")
    public List<UserResponseDto> getFollowing(@PathVariable String username) {
        return userService.getFollowing(username);
    }

}
