package com.cooksys.assessment2.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="user-table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Timestamp joined;

    @Column(nullable = false)
    private boolean deleted;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;


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
