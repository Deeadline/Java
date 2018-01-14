package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;
import jdk.nashorn.internal.runtime.logging.Logger;

import java.io.IOException;

public class Deposit implements Transfer {

    private final BankAccount account;
    private final int cash;
    private final String title = "Deposit to: ";
    private final Swift bankSwiftNumber;

    public Deposit(BankAccount account, int cash, Swift bankSwiftNumber) {
        this.account = account;
        this.cash = cash;
        this.bankSwiftNumber = bankSwiftNumber;
    }

    @Override
    public void execute() throws IOException {
        account.deposit(cash);
        BankProvider.getBankProviderInstance().getBank(bankSwiftNumber).addPayments(this);
        BankProvider.getBankProviderInstance().getUser(account).addPayment(this);
        account.addPayment(this);
        if (!FileManager.getFile().isFileExist("Payments.txt"))
            FileManager.getFile().openFile("Payments.txt");
        FileManager.getFile().saveToFile("Payments.txt", this.toString());
        BankProvider.getBankProviderInstance().updateHistory(account.getAccountNumber(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(account).getSurname(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(bankSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(bankSwiftNumber).toString());
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "title='" + title + '\'' +
                ", account=" + account.getAccountNumber() +
                ", cash=" + cash +
                ", bankSwiftNumber=" + bankSwiftNumber +
                '}';
    }
}