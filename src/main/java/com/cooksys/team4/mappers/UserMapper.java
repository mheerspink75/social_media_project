package com.cooksys.team4.mappers;

import com.cooksys.team4.dtos.UserRequestDto;
import com.cooksys.team4.dtos.UserResponseDto;
import com.cooksys.team4.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User requestDtoToEntity(UserRequestDto userRequestDto);

    UserResponseDto entityToResponseDto(User user);

    List<UserResponseDto> entitiesToResponseDtos(List<User> users);


}
