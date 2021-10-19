package com.cooksys.team4.parsers;

import java.util.Set;

public interface TweetParser {
    public Set<String> parseMentions(String input);
    public Set<String> parseHashtags(String input);
}
