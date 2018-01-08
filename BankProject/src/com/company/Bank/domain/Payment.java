package com.company.Bank.domain;

public class Payment {
    private String title;

    public Payment(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "title='" + title + '\'' +
                '}';
    }
}
