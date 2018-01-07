package com.company.Bank.domain;

import java.util.Objects;

public class BankAccount {
    private String bankAccountNumber;
    private int accountBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return accountBalance == that.accountBalance &&
                Objects.equals(bankAccountNumber, that.bankAccountNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bankAccountNumber);
    }

    public BankAccount(String bankAccountNumber) {
        this.accountBalance = 0;
        this.bankAccountNumber = bankAccountNumber;

    }

    public String getAccountNumber() {
        return bankAccountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    @Override
    public String toString() {
        return "Account='" + bankAccountNumber + '\'' +
                ", Account balance=" + accountBalance;
    }

    public int deposit(int value) {
        this.accountBalance += value;
        return accountBalance;
    }

    public int withdraw(int value) {
        this.accountBalance -= value;
        return accountBalance;
    }

}
