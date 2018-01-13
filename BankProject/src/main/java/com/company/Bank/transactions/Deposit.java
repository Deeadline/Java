package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Payment;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;

import java.io.IOException;

public class Deposit {

    public void deposit(BankAccount account, int money, Payment paymentTitle, Swift swiftNumber) throws IOException {
        account.deposit(money);
        BankProvider.getBankProviderInstance().getBank(swiftNumber).addPayments(paymentTitle);
        BankProvider.getBankProviderInstance().getUser(account).addPayment(paymentTitle);
        account.addPayment(paymentTitle);
        if (!FileManager.getFile().isFileExist(Payment.getPaymentFile()))
            FileManager.getFile().openFile(Payment.getPaymentFile());
        FileManager.getFile().saveToFile(Payment.getPaymentFile(), paymentTitle.toString());
        BankProvider.getBankProviderInstance().updateHistory(account.getAccountNumber(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(account).getSurname(), account.toString());
        BankProvider.getBankProviderInstance().updateHistory(swiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumber).toString());
    }
}