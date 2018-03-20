package com.company.inheritance;

public class Cat  extends Animal{
    public Cat() {
        super();
    }

    public Cat(String n) {
        super(n);
    }

    protected String getType() {
        return "Cat";
    }

    protected String getVoice() {
        return "Miauuu...";
    }
}
