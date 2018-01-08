package com.company.Bank.domain;


import com.company.Bank.provider.BankProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {
    private String bankAccountNumber;
    private int accountBalance = 0;
    private final List<Payment> paymentsList = new ArrayList<>();

    public BankAccount(String bankAccountNumber) {
        for (Bank bank : BankProvider.getBankProviderInstance().getAllBanks()) {
            for (BankAccount bankAccount : bank.getBankAccountList()) {
                if (bankAccount.getAccountNumber().equals(bankAccountNumber))
                    throw new IllegalArgumentException("There cannot exists 2 account with one accountNumber");
            }
        }
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getAccountNumber() {
        return bankAccountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public int deposit(int value) {
        if (value > 0) {
            this.accountBalance += value;
            return accountBalance;
        }
        return 0;
    }

    public int withdraw(int value) {
        if (value > 0) {
            this.accountBalance -= value;
            return accountBalance;
        }
        return 0;
    }

    public void addPayment(Payment payment) {
        paymentsList.add(payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BankAccount)) return false;
        BankAccount account = (BankAccount) o;
        return Objects.equals(bankAccountNumber, account.bankAccountNumber);
    }

    @Override
    public String toString() {
        return "bankAccountNumber='" + bankAccountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ", paymentsList=" + paymentsList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountNumber);
    }

}
