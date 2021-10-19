package com.cooksys.team4.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor
@Data
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp joined;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

    @ManyToMany
    private List<Tweet> likedTweets;

    @ManyToMany(mappedBy = "followers")
    private List<User> following;

    @ManyToMany
    private List<User> followers;

    @Column(nullable = false)
    @ManyToMany
    private List<Tweet> mentions;


}
