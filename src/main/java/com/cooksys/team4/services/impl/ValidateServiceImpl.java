package com.cooksys.team4.services.impl;

import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.entities.User;
import com.cooksys.team4.exceptions.BadRequestException;
import com.cooksys.team4.mappers.UserMapper;
import com.cooksys.team4.repositories.HashTagRepository;
import com.cooksys.team4.repositories.UserRepository;
import com.cooksys.team4.services.ValidateService;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;

    /**
     * TODO: implement Checks whether or not a given username exists.
     *
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validateusernameexistsusername">...</a>
     */
    @Override
    public boolean usernameExists(String username) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getCredentials().getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: implement Checks whether or not a given username is available.
     *
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validateusernameavailableusername">...</a>
     */
    @Override
    public boolean usernameAvailable(String username) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getCredentials().getUsername().equals(username)) {
                throw new BadRequestException("A user with username : " + username + " already exists");
            }
        }
        return true;
    }

    /**
     * TODO: implement Checks whether or not a given hashtag exists.
     *
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validatetagexistslabel">...</a>
     */
    @Override
    public boolean hashtagExists(String label) {
        Optional<Hashtag> optionalHashtag = hashTagRepository.findOneByLabelIgnoreCase(label);
        if (optionalHashtag.isPresent()) {
            Hashtag hashtag1 = optionalHashtag.get();
            if (hashtag1.getLabel().equals(label)) {
                return true;
            }
        }
        return false;
    }
}
