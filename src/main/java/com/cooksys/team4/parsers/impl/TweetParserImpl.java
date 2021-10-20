package com.cooksys.team4.parsers.impl;

import java.util.Set;

import com.cooksys.team4.parsers.TweetParser;

public class TweetParserImpl implements TweetParser {
    @Override
    public Set<String> parseMentions(String input) {
        return Set.of();
    }

    @Override
    public Set<String> parseHashtags(String input) {
        return Set.of();
    }
}
