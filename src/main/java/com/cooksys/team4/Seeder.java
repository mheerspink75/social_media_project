package com.cooksys.team4;


import com.cooksys.team4.entities.Credentials;
import com.cooksys.team4.entities.Profile;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.entities.User;
import com.cooksys.team4.repositories.HashTagRepository;
import com.cooksys.team4.repositories.TweetRepository;
import com.cooksys.team4.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final HashTagRepository hashTagRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // --- User 1 ---
        // Credentials
        Credentials user1Cred = new Credentials();
        user1Cred.setUsername("User1");
        user1Cred.setPassword("Pass1");

        User user1 = new User();
        user1.setCredentials(user1Cred);

        // Profile
        Profile user1Pro = new Profile();
        user1Pro.setFirstName("Johnny");
        user1Pro.setLastName("Appleseed");
        user1Pro.setEmail("user2@email.com");
        user1Pro.setPhone("123-456-7890");
        user1.setProfile(user1Pro);
        // Deleted
        user1.setDeleted(false);
        userRepository.saveAndFlush(user1);


        // --- User 2 ---
        // Credentials
        Credentials user2Cred = new Credentials();
        user2Cred.setUsername("User2");
        user2Cred.setPassword("Password");

        User user2 = new User();
        user2.setCredentials(user2Cred);

        // Profile
        Profile user2Pro = new Profile();
        user2Pro.setFirstName("John");
        user2Pro.setLastName("Smith");
        user2Pro.setEmail("user2@email.com");
        user2Pro.setPhone("234-567-8901");
        user2.setProfile(user2Pro);
        // Deleted
        user2.setDeleted(false);
        userRepository.saveAndFlush(user2);


        // --- User 3 ---
        Credentials user3Cred = new Credentials();
        // Credentials
        user3Cred.setUsername("User3");
        user3Cred.setPassword("Password");

        User user3 = new User();
        user3.setCredentials(user3Cred);

        // Profile
        Profile user3Pro = new Profile();
        user3Pro.setFirstName("Jill");
        user3Pro.setLastName("Jillin");
        user3Pro.setEmail("user3@email.com");
        user3Pro.setPhone("345-678-9012");
        user3.setProfile(user3Pro);
        // Deleted
        user3.setDeleted(false);
        userRepository.saveAndFlush(user3);


        // --- User 4 ---
        // Credentials
        Credentials user4Cred = new Credentials();
        user4Cred.setUsername("User4");
        user4Cred.setPassword("Password");

        User user4 = new User();
        user4.setCredentials(user4Cred);

        // Profile
        Profile user4Pro = new Profile();
        user4Pro.setFirstName("Bob");
        user4Pro.setLastName("Dole");
        user4Pro.setEmail("user4@email.com");
        user4Pro.setPhone("456-789-0023");
        user4.setProfile(user4Pro);
        // Deleted
        user4.setDeleted(false);
        userRepository.saveAndFlush(user4);


        // --- User 5 ---
        // Credentials
        Credentials user5Cred = new Credentials();
        user5Cred.setUsername("User5");
        user5Cred.setPassword("Password");

        User user5 = new User();
        user5.setCredentials(user5Cred);

        // Profile
        Profile user5Pro = new Profile();
        user5Pro.setFirstName("Da");
        user5Pro.setLastName("Bears");
        user5Pro.setEmail("user5@email.com");
        user5Pro.setPhone("567-890-0034");
        user5.setProfile(user5Pro);
        // Deleted
        user5.setDeleted(false);
        userRepository.saveAndFlush(user5);


        // ----- TWEETS -----

        // --- Start Tweet 1 ---
        Tweet tweet1 = new Tweet();
        tweet1.setAuthor(user1);
        tweet1.setDeleted(false);
        tweet1.setContent("This is some content 1");
        tweetRepository.saveAndFlush(tweet1);

        // --- Start Tweet 2 ---
        Tweet tweet2 = new Tweet();
        tweet2.setAuthor(user1);
        tweet2.setDeleted(false);
        tweet2.setContent("This is some content 2");
        tweetRepository.saveAndFlush(tweet2);

        // --- Start Tweet 3 ---
        Tweet tweet3 = new Tweet();
        tweet3.setAuthor(user2);
        tweet3.setDeleted(false);
        // Set Content @PARAM String
        tweet3.setContent("This is some content 3");
        tweetRepository.saveAndFlush(tweet3);


        // --- Start Tweet 4 ---
        Tweet tweet4 = new Tweet();
        tweet4.setAuthor(user2);
        tweet4.setDeleted(false);
        // Set Content @PARAM String
        tweet4.setContent("This is some content 4");
        tweetRepository.saveAndFlush(tweet4);

        // --- Start Tweet 5 ---
        Tweet tweet5 = new Tweet();
        tweet5.setAuthor(user3);
        tweet5.setDeleted(false);
        // Set Content @PARAM String
        tweet5.setContent("This is some content 5");
        tweetRepository.saveAndFlush(tweet5);

        // --- Start Tweet 6 ---
        Tweet tweet6 = new Tweet();
        tweet6.setAuthor(user3);
        tweet6.setDeleted(false);
        // Set Content @PARAM String
        tweet6.setContent("This is some content 6");
        tweetRepository.saveAndFlush(tweet6);

        // ----- LIST of Tweets + Adding to User# -----
        List<Tweet> user1Tweets = List.of(tweet1, tweet2);
        user1.setTweets(user1Tweets);
        userRepository.saveAndFlush(user1);

        List<Tweet> user2Tweets = List.of(tweet3, tweet4);
        user2.setTweets(user2Tweets);
        userRepository.saveAndFlush(user2);

        List<Tweet> user3Tweets = List.of(tweet5, tweet6);
        user3.setTweets(user3Tweets);
        userRepository.saveAndFlush(user3);


        // ----- List of Liked Tweets -----
        user1.setLikedTweets(user3Tweets);
        userRepository.saveAndFlush(user1);

        user2.setLikedTweets(user1Tweets);
        userRepository.saveAndFlush(user2);

        user3.setLikedTweets(user2Tweets);
        userRepository.saveAndFlush(user3);

        // ----- List of Following -----
        List<User> followingList = List.of(user2, user3, user4);
        user1.setFollowing(followingList);
        userRepository.saveAndFlush(user1);
        // ----- List of Followers -----
        List<User> followersList = List.of(user3, user5);
        user1.setFollowers(followersList);
        userRepository.saveAndFlush(user1);

        // ----- Tweet Mentions -----
        Tweet mention1 = new Tweet();
        mention1.setAuthor(user2);
        mention1.setDeleted(false);
        // Set Content @PARAM String
        mention1.setContent("This is some content for tweet mention 1");
        tweetRepository.saveAndFlush(mention1);

//        List<Tweet> mentionsList = List.of(mention1);
//        user1.setMentions(mentionsList);
//        userRepository.saveAndFlush(user1);
    }

}
