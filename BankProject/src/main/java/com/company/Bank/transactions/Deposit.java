package main.java.com.company.Bank.transactions;

import main.java.com.company.Bank.controllers.FileManager;
import main.java.com.company.Bank.domain.BankAccount;
import main.java.com.company.Bank.domain.Swift;
import main.java.com.company.Bank.provider.BankProvider;

import java.io.IOException;

public class Deposit extends Transaction {
    public Deposit() {
        super();
    }

    public void deposit(BankAccount account, int money, String title, Swift swiftNumber) throws IOException {
        account.deposit(money);
        setPayment(title);
        BankProvider.getBankProviderInstance().getBank(swiftNumber).addPayments(getPayment());
        BankProvider.getBankProviderInstance().getUser(account).addPayment(getPayment());
        account.addPayment(getPayment());
        if (!FileManager.getFile().isFileExist("Payments.txt"))
            FileManager.getFile().openFile("Payments.txt");
        FileManager.getFile().saveToFile("Payments.txt", title);
        BankProvider.getBankProviderInstance().updateHistory(account.getAccountNumber(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(account).getSurname(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(swiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumber).toString());
    }
}