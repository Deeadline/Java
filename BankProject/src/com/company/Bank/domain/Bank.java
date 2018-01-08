package com.company.Bank.domain;

import java.util.*;

public class Bank {
    private Swift swiftNumber;
    private List<BankAccount> bankAccountList = new ArrayList<>();



    public Bank(Swift swiftNumber) {
        this.swiftNumber = swiftNumber;
    }

    public Swift getSwiftNumber(){
        return swiftNumber;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "swiftNumber=" + swiftNumber +
                ", bankAccountList=" + bankAccountList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Bank)) return false;
        Bank bank = (Bank) o;
        return Objects.equals(swiftNumber, bank.swiftNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(swiftNumber);
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }
}
