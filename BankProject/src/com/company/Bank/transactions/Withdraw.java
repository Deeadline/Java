package com.company.Bank.transactions;

import com.company.Bank.controllers.FileManager;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Swift;
import com.company.Bank.provider.BankProvider;

import java.io.IOException;

public class Withdraw extends Transaction {
    public Withdraw() {
        super();
    }

    public void cashWithdrawer(BankAccount account, int money, String title, Swift swiftNumber) throws IOException {
        account.withdraw(money);
        setPayment(title);
        BankProvider.getBankProviderInstance().getBank(swiftNumber).addPayments(getPayment());
        BankProvider.getBankProviderInstance().getUser(account).addPayment(getPayment());
        account.addPayment(getPayment());
        if (!FileManager.getFile().isFileExist("Payments.txt"))
            FileManager.getFile().openFile("Payments.txt");
        FileManager.getFile().saveToFile("Payments.txt", getPayment().toString());
        BankProvider.getBankProviderInstance().updateHistory(account.getAccountNumber(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(account).getSurname(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(swiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumber).toString());
    }

    public void transferWithdrawer(BankAccount firstAccount, BankAccount secondAccount, int money, String title, Swift firstSwiftNumber) throws IOException {
        firstAccount.withdraw(money);
        secondAccount.deposit(money);
        setPayment(title);
        BankProvider.getBankProviderInstance().getBank(firstSwiftNumber).addPayments(getPayment());
        BankProvider.getBankProviderInstance().getUser(firstAccount).addPayment(getPayment());
        BankProvider.getBankProviderInstance().getUser(secondAccount).addPayment(getPayment());
        firstAccount.addPayment(getPayment());
        secondAccount.addPayment(getPayment());
        if (!FileManager.getFile().isFileExist("Payments.txt"))
            FileManager.getFile().openFile("Payments.txt");
        FileManager.getFile().saveToFile("Payments.txt", getPayment().toString());
        BankProvider.getBankProviderInstance().updateHistory(firstAccount.getAccountNumber(), firstAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(secondAccount.getAccountNumber(), secondAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(firstAccount).getSurname(), firstAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(secondAccount).getSurname(), secondAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(firstSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(firstSwiftNumber).toString());
    }
}
