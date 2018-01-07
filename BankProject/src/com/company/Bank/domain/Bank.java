package com.company.Bank.domain;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    private static Bank bankInstance = null;
    private final String bankName = "MBank";
    public static Bank getBankInstance() {
        if (bankInstance == null) {
            bankInstance = new Bank();
        }
        return bankInstance;
    }

    private Bank() {
    }

    @Override
    public String toString() {
        return "bankName='" + bankName + '\'' +
                ", bankAccountList=" + getAccountNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(bankName, bank.bankName) &&
                Objects.equals(bankAccountList, bank.bankAccountList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bankName, bankAccountList);
    }
    private List<String> getAccountNumber(){
        List<String> accountNumber = new ArrayList<>();
        for(BankAccount bankAccount : bankAccountList){
            accountNumber.add(bankAccount.getAccountNumber() + " ");
        }
        return accountNumber;
    }
    private List<BankAccount> bankAccountList = new ArrayList<>();

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public String getName() {
        return bankName;
    }
}
