package com.cooksys.team4.mappers;

import com.cooksys.team4.dtos.ProfileDto;
import com.cooksys.team4.entities.Profile;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile requestDtoToEntity(ProfileDto profileDto);

    ProfileDto entityToResponseDto(Profile profile);

}
