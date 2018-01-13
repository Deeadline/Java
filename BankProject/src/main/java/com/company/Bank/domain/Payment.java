package com.company.bank.domain;

public class Payment {
    private final String title;
    private static final String paymentFile = "Payments.txt";

    public Payment(String title) {
        this.title = title;
    }

    public static String getPaymentFile(){
        return  paymentFile;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "title='" + title + '\'' +
                '}';
    }
}
