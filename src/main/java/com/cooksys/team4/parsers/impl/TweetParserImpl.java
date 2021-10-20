package com.cooksys.team4.parsers.impl;

import com.cooksys.team4.parsers.PrefixedParser;
import com.cooksys.team4.parsers.TweetParser;
import java.util.Set;

public class TweetParserImpl implements TweetParser {
    private final PrefixedParser mentions = new PrefixedParserImpl("@");
    private final PrefixedParser hashtags = new PrefixedParserImpl("#");

    @Override
    public Set<String> parseMentions(String input) {
        return mentions.parse(input);
    }

    @Override
    public Set<String> parseHashtags(String input) {
        return hashtags.parse(input);
    }
}
