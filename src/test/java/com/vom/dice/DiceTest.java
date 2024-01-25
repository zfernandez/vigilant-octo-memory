package com.vom.dice;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        List<Dice> inputList = Arrays.asList(
            new Dice(4),
            new Dice(6),
            new Dice(10),
            new Dice(20)
        );

        /**
         * Bad test, relies on RNG
         **/
        Stream<DynamicTest> minimumMaximumRollStream = inputList.stream()
            .map(dice -> DynamicTest.dynamicTest(
                "minimumMaximumRoll: " + dice.size(),
                () -> {
                    int roll = dice.roll();
                    assertTrue(roll >= 1);
                    assertTrue(roll <= dice.size());
                }
            ));

        return minimumMaximumRollStream;
    }
}
