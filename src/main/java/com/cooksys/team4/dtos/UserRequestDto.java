package com.cooksys.team4.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data

public class UserRequestDto {
    private CredentialsDto credentials;
    private ProfileDto profile;
}
