package com.cooksys.team4.mappers;

import java.util.List;

import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.entities.Hashtag;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

    Hashtag requestDtoToEntity(HashTagDto hashTagDto);

    List<HashTagDto> entitiesToResponseDtos(List<Hashtag> hashtags);

}
