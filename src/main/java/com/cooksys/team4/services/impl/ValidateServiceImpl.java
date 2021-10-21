package com.cooksys.team4.services.impl;

import com.cooksys.team4.services.ValidateService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    /**
     * TODO: implement Checks whether or not a given username exists.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validateusernameexistsusername">...</a>
     */
    @Override
    public boolean usernameExists(String username) {
        return false;
    }

    /**
     * TODO: implement Checks whether or not a given username is available.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validateusernameavailableusername">...</a>
     */
    @Override
    public boolean usernameAvailable(String username) {
        return false;
    }

    /**
     * TODO: implement Checks whether or not a given hashtag exists.
     * 
     * @see <a href="https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validatetagexistslabel">...</a>
     */
    @Override
    public boolean hashtagExists(String hashtag) {
        return false;
    }
}
