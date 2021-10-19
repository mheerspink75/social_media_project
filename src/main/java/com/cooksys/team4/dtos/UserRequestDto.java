package com.cooksys.team4.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class UserRequestDto {

    private CredentialsDto credentials;

    private ProfileDto profile;

}
