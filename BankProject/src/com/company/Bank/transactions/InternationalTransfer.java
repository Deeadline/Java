package com.company.Bank.transactions;

import com.company.Bank.controllers.FileManager;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Swift;
import com.company.Bank.provider.BankProvider;

import java.io.IOException;

public class InternationalTransfer extends Transaction {
    public InternationalTransfer() {
        super();
    }

    public void internationalTransfer(Swift swiftNumberFirstBank, Swift swiftNumberSecondBank, String title, int money, BankAccount firstAccount, BankAccount secondAccount) throws IllegalArgumentException, IOException {
        if (BankProvider.getBankProviderInstance().getBank(swiftNumberFirstBank).getBankAccountList().contains(firstAccount)) {
            if (BankProvider.getBankProviderInstance().getBank(swiftNumberSecondBank).getBankAccountList().contains(secondAccount)) {
                firstAccount.withdraw(money);
                secondAccount.deposit(money);
                setPayment(title);
                BankProvider.getBankProviderInstance().getBank(swiftNumberFirstBank).addPayments(getPayment());
                BankProvider.getBankProviderInstance().getBank(swiftNumberSecondBank).addPayments(getPayment());
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
                BankProvider.getBankProviderInstance().updateHistory(swiftNumberFirstBank.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumberFirstBank).toString());
                BankProvider.getBankProviderInstance().updateHistory(swiftNumberSecondBank.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumberSecondBank).toString());
            }
        }
    }
}
