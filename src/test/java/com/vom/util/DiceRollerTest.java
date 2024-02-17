package com.vom.util;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DiceRollerTest {
    @TestFactory
    Stream<DynamicTest> DiceRoller_RNG_DiceRollsBetweenMinAndMax() {
        List<String> inputList = Arrays.asList(
            "d20",
            "d4",
            "d6",
            "d1",
            "d0"
        );
        List<String> outputRegexList = Arrays.asList(
            "Rolled: d20 = ([\\d]+) = ([\\d]+)",
            "Rolled: d4 = ([\\d]+) = ([\\d]+)",
            "Rolled: d6 = ([\\d]+) = ([\\d]+)",
            "Rolled: d1 = ([\\d]+) = ([\\d]+)",
            "Dice must be positive: d0"
        );

        return inputList.stream()
            .map(dice -> DynamicTest.dynamicTest(
                "rollNumber: " + dice,
                () -> {
                    DiceRoller roller = new DiceRoller();
                    String result = roller.diceRoll(dice);
                    System.out.println(result);
                    assertFalse(result.isEmpty());

                    String regex = outputRegexList.get(inputList.indexOf(dice));
                    Pattern fullPattern = Pattern.compile(regex);
                    Matcher fullMatcher = fullPattern.matcher(result);
                    assertTrue(fullMatcher.find());

                    if (fullMatcher.groupCount() > 1) {
                        int matchOne = Integer.parseInt(fullMatcher.group(1));
                        int max = Integer.parseInt(dice.substring(1));
                        assertTrue(1 <= matchOne);
                        assertTrue(matchOne <= max);
                        int matchTwo = Integer.parseInt(fullMatcher.group(2));
                        assertTrue(1 <= matchTwo);
                        assertTrue(matchTwo <= max);
                    } else {
                        assertEquals(regex, result);
                    }
                }
            ));
    }
}
