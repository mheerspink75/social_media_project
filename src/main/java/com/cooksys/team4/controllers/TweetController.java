package com.cooksys.team4.controllers;


import java.util.List;

import com.cooksys.team4.services.TweetService;
import com.cooksys.team4.dtos.TweetRequestDto;
import com.cooksys.team4.dtos.TweetResponseDto;
import com.cooksys.team4.dtos.ErrorDto;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("tweets")


public class TweetController {

    // private TweetService tweetService;

}
