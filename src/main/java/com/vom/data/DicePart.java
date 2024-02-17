package com.vom.data;

public class DicePart extends DiceFormulaPart {
    private String roll;

    public DicePart(String partString) {
        super(partString);
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getRoll() {
        return roll;
    }
}
