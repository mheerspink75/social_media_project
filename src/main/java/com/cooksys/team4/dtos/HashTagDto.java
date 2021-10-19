package com.cooksys.team4.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashTagDto {

    private String label;

    private Timestamp firstUsed;

    private Timestamp lastUsed;

}
