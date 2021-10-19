package com.cooksys.team4.repositories;


import com.cooksys.team4.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
}
