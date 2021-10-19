package com.cooksys.team4.dtos;

import com.cooksys.team4.entities.Tweet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ContextDto {

    private Tweet target;

    private List<TweetResponseDto> before;

    private List<TweetResponseDto> after;


}
