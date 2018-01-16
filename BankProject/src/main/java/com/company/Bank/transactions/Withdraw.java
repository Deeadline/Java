package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;

import java.io.IOException;

public class Withdraw implements Transfer {
    private final BankAccount account;
    private final int cash;
    private final String title = "Withdraw from : ";
    private final Swift bankSwiftNumber;

    public Withdraw(BankAccount account, int cash, Swift bankSwiftNumber) {
        this.account = account;
        this.cash = cash;
        this.bankSwiftNumber = bankSwiftNumber;
    }

    @Override
    public void execute() throws IOException, RuntimeException {
        account.withdraw(cash);
        BankProvider.getBankProviderInstance().getBank(bankSwiftNumber).addPayments(this);
        BankProvider.getBankProviderInstance().getUser(account).addPayment(this);
        account.addPayment(this);
        if (!FileManager.getInstance().isFileExist("Payments.txt"))
            FileManager.getInstance().openFile("Payments.txt");
        FileManager.getInstance().saveToFile("Payments.txt", this.toString());
        BankProvider.getBankProviderInstance().updateHistory(account.getAccountNumber(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(account).getPESEL(), BankProvider.getBankProviderInstance().getUser(account).toString());
        BankProvider.getBankProviderInstance().updateHistory(bankSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(bankSwiftNumber).toString());
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "title='" + title + '\'' +
                ", account=" + account.getAccountNumber() +
                ", cash=" + cash +
                ", bankSwiftNumber=" + bankSwiftNumber +
                '}';
    }
}
