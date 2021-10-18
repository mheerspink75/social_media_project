package com.cooksys.assessment2.entities;

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

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn
    private Users author;

    @Column(nullable = false)
    private Timestamp posted;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @OneToMany(mappedBy = "replies")
    private Tweet inReplyTo;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn
    private List<Tweet> replies;

    @Column(nullable = false)
    @OneToMany(mappedBy = "reposts")
    private Tweet repostOf;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn
    private List<Tweet> reposts;

    @Column(nullable = false)
    private List<Users> likes;

    @Column(nullable = false)
    private List<Hashtag> hashtags;

    @Column(nullable = false)
    @ManyToMany(mappedBy = "tweets")
    private List<Users> usersMentioned;


}
