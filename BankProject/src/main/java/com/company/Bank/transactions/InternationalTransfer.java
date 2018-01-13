package com.company.bank.transactions;

import com.company.bank.controllers.FileManager;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Payment;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;

import java.io.IOException;

public class InternationalTransfer {

    public void internationalTransfer(Swift swiftNumberFirstBank, Swift swiftNumberSecondBank, Payment paymentTitle, int money, BankAccount firstAccount, BankAccount secondAccount) throws IllegalArgumentException, IOException {
        if (BankProvider.getBankProviderInstance().getBank(swiftNumberFirstBank).getBankAccountList().contains(firstAccount)) {
            if (BankProvider.getBankProviderInstance().getBank(swiftNumberSecondBank).getBankAccountList().contains(secondAccount)) {
                firstAccount.withdraw(money);
                secondAccount.deposit(money);
                BankProvider.getBankProviderInstance().getBank(swiftNumberFirstBank).addPayments(paymentTitle);
                BankProvider.getBankProviderInstance().getBank(swiftNumberSecondBank).addPayments(paymentTitle);
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
                BankProvider.getBankProviderInstance().updateHistory(swiftNumberFirstBank.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumberFirstBank).toString());
                BankProvider.getBankProviderInstance().updateHistory(swiftNumberSecondBank.toString(), BankProvider.getBankProviderInstance().getBank(swiftNumberSecondBank).toString());
            }
        }
    }
}
