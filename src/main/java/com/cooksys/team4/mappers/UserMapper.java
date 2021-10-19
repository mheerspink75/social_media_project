package com.cooksys.team4.mappers;

import java.util.List;

import com.cooksys.team4.dtos.UserRequestDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class })

public interface UserMapper {

    User requestDtoToEntity(UserRequestDto userRequestDto);

    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToResponseDto(User user);

    List<UserResponseDto> entitiesToResponseDtos(List<User> users);

}
