package com.cooksys.team4.parsers;

import com.cooksys.team4.parsers.impl.PrefixedParserImpl;

import java.util.List;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Testing: `mvn test -Dtest=PrefixedParserTests`
public class PrefixedParserTests {
    @Test
    public void testEmptyString() {
        final var parser = new PrefixedParserImpl("#");
        final var expected = new HashSet<>();
        final var actual = new HashSet<>(parser.parse(""));
        assertEquals(expected, actual);
    }

    @Test
    public void testNullInput() {
        final var parser = new PrefixedParserImpl("#");
        final var expected = new HashSet<>();
        final var actual = new HashSet<>(parser.parse(null));
        assertEquals(expected, actual);
    }

    @Test
    public void testSpaces() {
        final var parser = new PrefixedParserImpl("#");
        final var expected = new HashSet<>(List.of("yes", "no"));
        final var input = "#yes #no";
        final var actual = new HashSet<>(parser.parse(input));
        assertEquals(expected, actual);
    }

    @Test
    public void testPuncuation() {
        final var parser = new PrefixedParserImpl("@");
        final var expected = new HashSet<>(List.of("()[]", "j234k--"));
        final var input = "@()[]@ @j234k--";
        final var actual = new HashSet<>(parser.parse(input));
        assertEquals(expected, actual);
    }
}
