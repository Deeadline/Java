package com.company.bank.domain;

public enum Swift {
    BREXPLPWMUL("MBank"),
    BPKOPLPW("PKO BP"),
    PLPK0PL("Pekao s.a.");
    private final String text;

    Swift(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
