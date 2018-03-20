package com.company.inheritance;

/*final*/ public class Animal {
    private String name = "noName";

    public Animal() {
    }

    protected Animal(String n) {
        name = n;
    }

    private String getName() {
        return name;
    }

    protected String getType() {
        return "noName";
    }

    protected String getVoice() {
        return "?";
    }

    public void speak() {
        System.out.println(getType() + " " + getName() + " " + getVoice());
    }
}
