package com.vom.util;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        Stream<DynamicTest> rollNumberStream = inputList.stream()
            .map(dice -> DynamicTest.dynamicTest(
                "rollNumber: " + dice,
                () -> {
                    DiceRoller roller = new DiceRoller();
                    String result = roller.diceRoll(dice);
                    System.out.println("Rolled: " + result);
                    assertFalse(result.isEmpty(), "Rolled: " + result);

                    int roll = Integer.parseInt(result);
                    int min = 1;
                    int max = Integer.parseInt(dice.substring(dice.indexOf("d") + 1));

                    if (max >= min) {
                        System.out.println(min + " <= " + roll + " <= " + max);
                        assertTrue(roll >= min && roll <= max, min + " <= " + roll + " <= " + max);
                    } else {
                        System.out.println(max + " is not greater than or equal to " + min);
                        assertTrue(roll == 0);
                    }
                }
            ));

        return rollNumberStream;
    }
}
