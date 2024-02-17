package com.vom.data;

public abstract class DiceFormulaPart {
    private String partString;

    public DiceFormulaPart(String partString) {
        this.partString = partString;
    }

    public void setPartString(String partString) {
        this.partString = partString;
    }

    public String getPartString() {
        return partString;
    }

    public String toString() {
        return partString;
    }
}
