package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Payment;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;

import java.io.IOException;

public class Withdraw {

    public void cashWithdrawer(BankAccount account, int money, Payment paymentTitle, Swift swiftNumber) throws IOException {
        account.withdraw(money);
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

    public void transferWithdrawer(BankAccount firstAccount, BankAccount secondAccount, int money, Payment paymentTitle, Swift firstSwiftNumber) throws IOException {
        firstAccount.withdraw(money);
        secondAccount.deposit(money);
        BankProvider.getBankProviderInstance().getBank(firstSwiftNumber).addPayments(paymentTitle);
        BankProvider.getBankProviderInstance().getUser(firstAccount).addPayment(paymentTitle);
        BankProvider.getBankProviderInstance().getUser(secondAccount).addPayment(paymentTitle);
        firstAccount.addPayment(paymentTitle);
        secondAccount.addPayment(paymentTitle);
        if (!FileManager.getFile().isFileExist(Payment.getPaymentFile()))
            FileManager.getFile().openFile(Payment.getPaymentFile());
        FileManager.getFile().saveToFile(Payment.getPaymentFile(), paymentTitle.toString());
        BankProvider.getBankProviderInstance().updateHistory(firstAccount.getAccountNumber(), firstAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(secondAccount.getAccountNumber(), secondAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(firstAccount).getSurname(), firstAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(BankProvider.getBankProviderInstance().getUser(secondAccount).getSurname(), secondAccount.toString());
        BankProvider.getBankProviderInstance().updateHistory(firstSwiftNumber.toString(), BankProvider.getBankProviderInstance().getBank(firstSwiftNumber).toString());
    }
}
