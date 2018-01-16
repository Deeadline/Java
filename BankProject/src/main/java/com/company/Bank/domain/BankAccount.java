package com.company.bank.domain;

import com.company.bank.provider.BankProvider;
import com.company.bank.transactions.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount implements Savable {
    private String bankAccountNumber;
    private int accountBalance = 0;
    private final List<Transfer> paymentsList = new ArrayList<>();
    private String bankName;
    private String ownerPESEL;

    public BankAccount() {

    }

    public BankAccount(String bankAccountNumber, String bankName, String ownerPESEL) {
        for (Bank bank : BankProvider.getBankProviderInstance().getAllBanks()) {
            for (BankAccount bankAccount : bank.getBankAccountList()) {
                if (bankAccount.getAccountNumber().equals(bankAccountNumber))
                    throw new IllegalArgumentException("There cannot exists 2 account with one accountNumber");
            }
        }
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.ownerPESEL = ownerPESEL;
    }

    public String getAccountNumber() {
        return bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getOwnerPESEL() {
        return ownerPESEL;
    }

    public int deposit(int value) {
        if (value < 0)
            throw new IllegalArgumentException("You can't deposit minus value");
        this.accountBalance += value;
        return accountBalance;
    }

    public int withdraw(int value) {
        int accountBalance = this.accountBalance - value;
        if (this.accountBalance < 0 || value < 0)
            throw new IllegalArgumentException("You can't withdraw!");
        if(accountBalance < 0 ){
            throw new IllegalArgumentException("You can't run into debt!");
        }
        this.accountBalance -= value;
        return accountBalance;
    }

    public void addPayment(Transfer transfer) {
        paymentsList.add(transfer);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ",\r\n paymentsList=" + paymentsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BankAccount)) return false;
        BankAccount account = (BankAccount) o;
        return Objects.equals(bankAccountNumber, account.bankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountNumber);
    }

    @Override
    public void load(String content) {
        String[] parts = content.split(" - ");
        bankName = parts[0];
        bankAccountNumber = parts[1];
        accountBalance = Integer.parseInt(parts[2]);
        ownerPESEL = parts[3];
    }

    @Override
    public String save() {
        return bankName + " - " + bankAccountNumber + " - " + accountBalance + " - " + ownerPESEL;
    }
}
