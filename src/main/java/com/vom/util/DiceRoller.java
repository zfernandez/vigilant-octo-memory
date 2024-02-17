package com.vom.util;

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import com.vom.data.DiceFormulaPart;
import com.vom.data.DicePart;
import com.vom.data.NumberPart;
import com.vom.data.OperandPart;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;

import javax.script.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiceRoller {
    private final GraalJSScriptEngine engine;
    private final StringBuilder stringBuilder;

    public DiceRoller() {
        engine = GraalJSScriptEngine.create(
            Engine.newBuilder().option("engine.WarnInterpreterOnly", "false").build(),
            Context.newBuilder("js")
        );
        stringBuilder = new StringBuilder();
    }

    /**
     * Main roll method to determine comma splitting
     */
    public String diceRoll(String input) {
        String[] split = input.split(",");
        StringBuilder result = new StringBuilder();
        result.append("Rolled: ");
        for (int i = 0; i < split.length; i++) {
            List<DiceFormulaPart> partList = tokenizeRoll(split[i]);
            try {
                parseRoll(partList);
            } catch (VomException e) {
                return e.getLocalizedMessage();
            }
            result.append(calculateRoll(partList));
            if (i < split.length - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    /**
     * String splitter to break up formula string into commands
     */
    private List<DiceFormulaPart> tokenizeRoll(String input) {
        List<DiceFormulaPart> tokenizedItems = new ArrayList<>();

        String flags = "(kh[0-9]*|kl[0-9]*|)";
        String dice = "([0-9]*d[0-9]+" + flags + ")";
        String operand = "([^\\w\\s])";
        String number = "([0-9]+)";
        String regex = dice + "|" + operand + "|" + number;
        Pattern fullPattern = Pattern.compile(regex);
        Matcher fullMatcher = fullPattern.matcher(input);

        while (fullMatcher.find()) {
            String group = fullMatcher.group();
            if (Pattern.matches(dice, group)) {
                tokenizedItems.add(new DicePart(group));
            } else if (Pattern.matches(operand, group)) {
                tokenizedItems.add(new OperandPart(group));
            } else if (Pattern.matches(number, group)) {
                tokenizedItems.add(new NumberPart(group));
            }
        }

        return tokenizedItems;
    }

    /**
     * Secondary parse method switch to interpret commands and add everything in the end
     */
    private void parseRoll(List<DiceFormulaPart> tokenizedItems) throws VomException {
        Random random = new Random();
        int temp;

        for (DiceFormulaPart item : tokenizedItems) {
            Pattern dicePat = Pattern.compile("d[0-9]+");
            Matcher diceMat = dicePat.matcher(item.getPartString());
            if (!diceMat.find()) {
                continue;
            }

            int dice = Integer.parseInt(item.getPartString().substring(diceMat.start() + 1, diceMat.end()));
            if (dice < 1) {
                throw new VomException("Dice must be positive: d" + dice);
            }

            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.setLength(0);

            Pattern countPat = Pattern.compile("[0-9]+d");
            Matcher countMat = countPat.matcher(item.getPartString());
            if (countMat.find()) {
                stringBuilder.append("(");
                String countGroup = countMat.group();
                int repeat = Integer.parseInt(countGroup.substring(0, countGroup.length() - 1));
                for (int j = 0; j < repeat - 1; j++) {
                    temp = random.nextInt(dice) + 1;
                    stringBuilder.append(temp);
                    stringBuilder.append(" + ");
                }
                temp = random.nextInt(dice) + 1;
                stringBuilder.append(temp);
                stringBuilder.append(")");
            } else {
                temp = random.nextInt(dice) + 1;
                stringBuilder.append(temp);
            }

            ((DicePart)item).setRoll(stringBuilder.toString());
        }
    }

    /**
     * Build the final string with the calculated total, original parts and rolled values
     */
    private String calculateRoll(List<DiceFormulaPart> partList) {
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.setLength(0);

        for (DiceFormulaPart item : partList) {
            if (item instanceof DicePart) {
                stringBuilder.append(((DicePart)item).getRoll());
            } else {
                stringBuilder.append(item.getPartString());
            }
            stringBuilder.append(" ");
        }

        String eval = null;
        try {
            eval = engine.eval(stringBuilder.toString()).toString();
        } catch (ScriptException e) {
            System.err.println(e);
        }

        if (eval == null) {
            return "Couldn't evaluate";
        }

        int total = Integer.parseInt(eval);
        String equation = stringBuilder.toString();

        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.setLength(0);

        for (DiceFormulaPart item : partList) {
            stringBuilder.append(item.getPartString());
            stringBuilder.append(" ");
        }

        stringBuilder.append("= ");
        stringBuilder.append(equation);

        stringBuilder.append("= ");
        stringBuilder.append(total);

        return stringBuilder.toString();
    }
}
