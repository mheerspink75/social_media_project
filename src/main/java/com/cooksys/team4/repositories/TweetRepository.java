package com.cooksys.team4.repositories;

import com.cooksys.team4.entities.Tweet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByDeletedFalse();
    
    List<Tweet> findAllByDeletedOrderByPosted(boolean deleted);
    
}
