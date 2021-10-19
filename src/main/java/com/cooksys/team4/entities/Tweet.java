package com.cooksys.team4.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User author;

    @Column(nullable = false)
    private Timestamp posted;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Tweet inReplyTo;

    @OneToMany(mappedBy = "replies")
    private List<Tweet> replies;

    @ManyToOne
    private Tweet repostOf;

    @OneToMany(mappedBy = "reposts")
    private List<Tweet> reposts;

    @ManyToMany
    private List<User> likes;

    @ManyToMany
    private List<Hashtag> hashtags;

    @ManyToMany(mappedBy = "mentions")
    private List<User> userMentioned;


}
