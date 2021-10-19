package com.cooksys.team4.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data

public class ProfileDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
