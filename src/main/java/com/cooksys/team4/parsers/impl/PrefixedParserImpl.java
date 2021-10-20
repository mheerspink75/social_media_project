package com.cooksys.team4.parsers.impl;

import com.cooksys.team4.parsers.PrefixedParser;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.function.Function;

public class PrefixedParserImpl implements PrefixedParser {
    private final Pattern pattern;

    public PrefixedParserImpl(String prefix) {
        pattern = Pattern.compile(prefix + "([^" + prefix + "\\s]+)");
    }

    @Override
    public Set<String> parse(String input) {
        return Optional.ofNullable(input)
            .stream()
            .map(pattern::matcher)
            .flatMap(Matcher::results)
            .flatMap(safeGetGroup(1))
            .collect(Collectors.toSet());
    }

    /**
     * Helper to safely get a group
     * MatchResult::group can throw multiple exceptions or return null
     * @see https://docs.oracle.com/javase/7/docs/api/java/util/regex/MatchResult.html#group(int)
     */
    private static Function<MatchResult, Stream<String>> safeGetGroup(int idx) {
        return result -> {
            try {
                return Optional.ofNullable(result.group(idx)).stream();
            } catch (Exception e) {
                return Stream.empty();
            }
        }; 
    }
}
