package com.cooksys.assessment2.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Users {

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

    @Column(nullable = false)
    private List<Tweet> likedTweets;

    @Column(nullable = false)
    @ManyToMany(mappedBy = "followers")
    private List<Users> following;

    @Column(nullable = false)
    @ManyToMany(mappedBy = "following")
    private List<Users> followers;

    @Column(nullable = false)
    private List<Tweet> mentions;


}
