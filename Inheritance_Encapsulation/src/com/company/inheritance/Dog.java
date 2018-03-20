package com.company.inheritance;

public class Dog extends Animal {
    public Dog() {
        super();
    }

    public Dog(String n) {
        super(n);
    }

    protected String getType() {
        return "Dog";
    }

    protected String getVoice() {
        return "HAU, HAU...";
    }
}
