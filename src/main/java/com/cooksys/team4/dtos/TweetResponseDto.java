package com.cooksys.team4.dtos;

import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@NoArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;

    private User author;

    private Timestamp posted;

    private String content;

    private Tweet inReplyTo;

    private Tweet repostOf;

}
