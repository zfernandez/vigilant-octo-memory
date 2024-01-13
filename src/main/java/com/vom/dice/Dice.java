package com.vom.dice;

import java.util.Random;

public class Dice {
    private int size;
    private Random random;

    public Dice(int size) {
        this.size = size;
        random = new Random();
    }

    public int roll() {
        return random.ints(1, 1, size + 1).toArray()[0];
    }
}
