package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;

import java.io.IOException;

public class InternationalTransfer implements Transfer {

    private final BankAccount firstAccount;
    private final BankAccount secondAccount;
    private final int cash;
    private final String title;
    private final Swift firstBankSwiftNumber;
    private final Swift secondBankSwiftNumber;

    public InternationalTransfer(BankAccount firstAccount, BankAccount secondAccount, int cash, String title, Swift firstBankSwiftNumber, Swift secondBankSwiftNumber) {
        this.firstAccount = firstAccount;
        this.secondAccount = secondAccount;
        this.cash = cash;
        this.title = title;
        this.firstBankSwiftNumber = firstBankSwiftNumber;
        this.secondBankSwiftNumber = secondBankSwiftNumber;
    }

    @Override
    public void execute() throws IOException, RuntimeException {
        if (BankProvider.getBankProviderInstance().getBank(firstBankSwiftNumber).getBankAccountList().contains(firstAccount)) {
            if (BankProvider.getBankProviderInstance().getBank(secondBankSwiftNumber).getBankAccountList().contains(secondAccount)) {
                firstAccount.withdraw(cash);
                secondAccount.deposit(cash);
                BankProvider.getBankProviderInstance().getBank(firstBankSwiftNumber).addPayments(this);
                BankProvider.getBankProviderInstance().getBank(secondBankSwiftNumber).addPayments(this);
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
                BankProvider.getBankProviderInstance().updateHistory(firstBankSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(firstBankSwiftNumber).toString());
                BankProvider.getBankProviderInstance().updateHistory(secondBankSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(secondBankSwiftNumber).toString());
            }
        }
    }

    @Override
    public String toString() {
        return "InternationalTransfer{" +
                "title='" + title + '\'' +
                ", firstAccount=" + firstAccount.getAccountNumber() +
                ", secondAccount=" + secondAccount.getAccountNumber() +
                ", cash=" + cash +
                ", firstBankSwiftNumber=" + firstBankSwiftNumber +
                ", secondBankSwiftNumber=" + secondBankSwiftNumber +
                '}';
    }
}
