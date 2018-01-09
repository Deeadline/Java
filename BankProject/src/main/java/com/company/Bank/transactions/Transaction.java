package main.java.com.company.Bank.transactions;

import main.java.com.company.Bank.domain.Payment;

public class Transaction {
    private final Payment payment = new Payment();

    Transaction() {
    }

    Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "payment=" + payment +
                '}';
    }

    void setPayment(String title) {
        this.payment.setTitle(title);
    }


}
