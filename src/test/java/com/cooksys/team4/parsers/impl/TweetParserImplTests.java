package com.cooksys.team4.parsers.impl;

import com.cooksys.team4.parsers.TweetParser;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Testing: `mvn test -Dtest=TweetParserImplTests`
public class TweetParserImplTests {
    private final TweetParser parser = new TweetParserImpl();

    @Test
    public void testEmptyHashtags() {
        assertTrue(Set.of().containsAll(parser.parseHashtags("")));
    }

    @Test
    public void testEmptyMentions() {
        assertTrue(Set.of().containsAll(parser.parseMentions("")));
    }
}
