package com.cooksys.team4.mappers;

import com.cooksys.team4.dtos.CredentialsDto;
import com.cooksys.team4.entities.Credentials;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials requestToEntity(CredentialsDto credentialsDto);

    CredentialsDto entityToResponseDto(Credentials credentials);

}
