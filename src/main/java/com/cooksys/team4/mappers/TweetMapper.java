package com.cooksys.team4.mappers;

import java.util.List;

import com.cooksys.team4.dtos.TweetRequestDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.entities.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    Tweet requestDtoToEntity(TweetRequestDto tweetRequestDto);

    TweetResponseDto entityToResponseDto(Tweet tweet);

    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);


}
