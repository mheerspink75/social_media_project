package com.cooksys.team4.dtos;

import com.cooksys.team4.entities.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data

public class UserResponseDto {

    private String username;

    private Profile profile;

    private Timestamp joined;
}
