package com.company.Bank.domain;


import java.util.Objects;

public class BankAccount {
    private String bankAccountNumber;
    private int accountBalance = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BankAccount)) return false;
        BankAccount account = (BankAccount) o;
        return accountBalance == account.accountBalance &&
                Objects.equals(bankAccountNumber, account.bankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountNumber, accountBalance);
    }

    public BankAccount(String bankAccountNumber) {
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
        return "bankAccountNumber='" + bankAccountNumber + '\'' +
                ", accountBalance=" + accountBalance;
    }

    public int deposit(int value) {
        if(value>0) {
            this.accountBalance += value;
            return accountBalance;
        }
        return 0;
    }

    public int withdraw(int value) {
        if(value>0) {
            this.accountBalance -= value;
            return accountBalance;
        }
        return 0;
    }

}
