package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;

import java.io.IOException;

public class DomesticTransfer implements Transfer {
    private final BankAccount firstAccount;
    private final BankAccount secondAccount;
    private final int cash;
    private final String title;
    private final Swift bankSwiftNumber;

    public DomesticTransfer(BankAccount firstAccount, BankAccount secondAccount, int cash, String title, Swift bankSwiftNumber) {
        this.firstAccount = firstAccount;
        this.secondAccount = secondAccount;
        this.cash = cash;
        this.title = title;
        this.bankSwiftNumber = bankSwiftNumber;
    }

    @Override
    public void execute() throws IOException, RuntimeException {
        firstAccount.withdraw(cash);
        secondAccount.deposit(cash);
        BankProvider.getBankProviderInstance().getBank(bankSwiftNumber).addPayments(this);
        BankProvider.getBankProviderInstance().getUser(firstAccount).addPayment(this);
        BankProvider.getBankProviderInstance().getUser(secondAccount).addPayment(this);
        firstAccount.addPayment(this);
        secondAccount.addPayment(this);
        if (!FileManager.getInstance().isFileExist("Payments.txt"))
            FileManager.getInstance().openFile("Payments.txt");
        FileManager.getInstance().saveToFile("Payments.txt", this.toString());
        BankProvider.getBankProviderInstance().updateHistory(firstAccount.getAccountNumber(), firstAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(secondAccount.getAccountNumber(), secondAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(firstAccount).getPESEL(), BankProvider.getBankProviderInstance().getUser(firstAccount).toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(secondAccount).getPESEL(), BankProvider.getBankProviderInstance().getUser(secondAccount).toString());
        BankProvider.getBankProviderInstance().updateHistory(bankSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(bankSwiftNumber).toString());
    }

    @Override
    public String toString() {
        return "DomesticTransfer{" +
                "title='" + title + '\'' +
                ", firstAccount=" + firstAccount.getAccountNumber() +
                ", secondAccount=" + secondAccount +
                ", cash=" + cash +
                ", bankSwiftNumber=" + bankSwiftNumber +
                '}';
    }
}
