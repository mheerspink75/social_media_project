package com.cooksys.team4.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
public class HashTagDto {

        private String label;

        private Timestamp firstUsed;

        private Timestamp lastUsed;

}
