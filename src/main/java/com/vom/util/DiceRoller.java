package com.vom.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiceRoller {
    public DiceRoller() {

    }

    /**
     * Main roll method to determine comma splitting
     */
    public String diceRoll(String input) {
        String[] split = input.split(",");
        int total = 0;
        for (String s : split) {
            total += parseRoll(s);
        }
        return Integer.toString(total);
    }

    /**
     * Secondary parse method switch to interpret commands and add everything in the end
     */
    private int parseRoll(String input) {
        // Roll total
        int roll = 0;

        // TODO: Calculate various option flags

        // TODO: Calculate multiple dice "#d##"
        // Calculate actual dice roll of "d##"
        // "d[0-9]+"
        Pattern pattern = Pattern.compile("d[0-9]+");
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        int dice = Integer.parseInt(matcher.group().substring(1));

        if (dice > 0) {
            // primitive adding roll to variable
            // TODO: change based on statement
            roll += calculateRoll(dice);
        }

        // Return total roll result
        return roll;
    }

    /**
     * Actual random roll method to generate a number based on the dice
     */
    private int calculateRoll(int dice) {
        Random random = new Random();
        return random.nextInt(dice) + 1;
    }
}
