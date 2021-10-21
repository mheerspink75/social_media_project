package com.cooksys.team4.services.impl;

import java.util.List;

import com.cooksys.team4.dtos.HashTagDto;
import com.cooksys.team4.entities.Hashtag;
import com.cooksys.team4.entities.Tweet;
import com.cooksys.team4.mappers.HashtagMapper;
import com.cooksys.team4.repositories.HashTagRepository;
import com.cooksys.team4.services.HashTagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class HashtagServiceImpl implements HashTagService {

    private final HashTagRepository hashTagRepository;
    private final HashtagMapper hashtagMapper;

    @Override
    public List<HashTagDto> getAllHashtags() {
        return hashtagMapper.entitiesToResponseDtos(hashTagRepository.findAll());
    }

    @Override
    public List<Tweet> getTweetsByHashtagLabel(Hashtag label) {
        return null;
    }
}
