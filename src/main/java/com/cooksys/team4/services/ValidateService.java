package com.cooksys.team4.services;

public interface ValidateService {
    /**
     * Checks whether or not a given username exists.
     * 
     * @see https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validateusernameexistsusername
     */
    public boolean usernameExists(String username);

    /**
     * Checks whether or not a given username is available.
     * 
     * @see https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validateusernameavailableusername
     */
    public boolean usernameAvailable(String username);

    /**
     * Checks whether or not a given hashtag exists.
     * 
     * @see https://github.com/fasttrackd-student-work/spring-assessment-social-media-team4#get---validatetagexistslabel
     */
    public boolean hashtagExists(String hashtag);
}
