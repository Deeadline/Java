package com.company.Bank.provider;


import com.company.Bank.controllers.FileManager;
import com.company.Bank.domain.*;
import com.company.Bank.transactions.Deposit;
import com.company.Bank.transactions.InternationalTransfer;
import com.company.Bank.transactions.Withdraw;

import java.io.IOException;
import java.util.*;

public class BankProvider {
    private static final List<Person> users = new ArrayList<>();
    private static final List<Bank> bankList = new ArrayList<>();
    private static BankProvider bankProviderInstance;

    private BankProvider() {
    }

    public static BankProvider getBankProviderInstance() {
        if (bankProviderInstance == null) {
            bankProviderInstance = new BankProvider();
        }
        return bankProviderInstance;
    }

    public boolean createNewBank(Swift swiftNumber) {
        if (getBank(swiftNumber) == null) {
            bankList.add(new Bank(swiftNumber));
            return true;
        }
        return false;
    }

    public List<Bank> getAllBanks() {
        return bankList;
    }

    public Bank getBank(Swift swift) {
        for (Bank bank : bankList) {
            if (bank.getSwiftNumber() == swift)
                return bank;
        }
        return null;
    }

    public List<Person> getUsers() {
        return users;
    }

    public Person getUser(BankAccount account) {
        for (Person user : users) {
            if (user.getAccount().contains(account))
                return user;
        }
        return null;
    }

    public void addUser(Person user) throws IOException {
        if (users.isEmpty()) {
            if (!FileManager.getFile().isFileExist(user.getSurname() + ".txt"))
                FileManager.getFile().openFile(user.getSurname() + ".txt");
            FileManager.getFile().saveToFile(user.getSurname() + ".txt", user.toString());
            users.add(user);
        } else {
            users.add(user);
            updateHistory(user.getSurname(), users.toString());
        }
    }


    public Boolean addAccount(Person accountUser, Swift swiftNumber) throws IOException {
        String numbers = new Random().ints(0, 9).boxed().filter(i -> i >= 0 && i <= 9).limit(18).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        if (getBank(swiftNumber).getBankAccountList().size() != 0) {
            for (BankAccount bankAccount : getBank(swiftNumber).getBankAccountList()) {
                if (bankAccount.getAccountNumber().equals(numbers))
                    return false;
            }
        }
        BankAccount account = new BankAccount(numbers);
        accountUser.addAccount(account);
        getBank(swiftNumber).addAccount(account);
        getBank(swiftNumber).addPerson(accountUser);

        updateHistory(swiftNumber.toString(), getBank(swiftNumber).toString());
        updateHistory(accountUser.getSurname(), users.toString());
        if (!FileManager.getFile().isFileExist(account.getAccountNumber() + ".txt"))
            FileManager.getFile().openFile(account.getAccountNumber() + ".txt");
        FileManager.getFile().saveToFile(account.getAccountNumber() + ".txt", account.toString());
        return true;
    }

    public void cashWithdrawer(BankAccount account, int money, Payment paymentTitle, Swift swiftNumber) throws IOException {
        Withdraw transfer = new Withdraw();
        transfer.cashWithdrawer(account, money, paymentTitle.toString(), swiftNumber);
    }

    public void transferWithdrawer(BankAccount firstAccount, BankAccount secondAccount, int money, Payment paymentTitle, Swift swiftNumber) throws IOException {
        Withdraw transfer = new Withdraw();
        transfer.transferWithdrawer(firstAccount, secondAccount, money, paymentTitle.toString(), swiftNumber);
    }

    public void internationalTransfer(Swift swiftBank1, Swift swiftBank2, Payment paymentTitle, int money, BankAccount firstAccount, BankAccount secondAccount) throws IOException {
        InternationalTransfer transfer = new InternationalTransfer();
        transfer.internationalTransfer(swiftBank1, swiftBank2, paymentTitle.toString(), money, firstAccount, secondAccount);
    }

    public void deposit(BankAccount account, int money, Payment paymentTitle, Swift swiftNumber) throws IOException {
        Deposit depositTransfer = new Deposit();
        depositTransfer.deposit(account, money, paymentTitle.toString(), swiftNumber);
    }

    public List<String> readPaymentsHistory() throws IOException {
        return FileManager.getFile().readFromFile("Payments.txt");
    }

    public List<String> readClientHistory(String surname) throws IOException {
        return FileManager.getFile().readFromFile(surname + ".txt");
    }

    public List<String> readAccountHistory(String accountNumber) throws IOException {
        return FileManager.getFile().readFromFile(accountNumber + ".txt");
    }

    public List<String> readBankHistory(String swiftNumber) throws IOException {
        return FileManager.getFile().readFromFile(swiftNumber + ".txt");
    }

    public void updateHistory(String path, String contents) throws IOException {
        if (FileManager.getFile().isFileExist(path + ".txt")) {
            FileManager.getFile().removeFile(path + ".txt");
            FileManager.getFile().openFile(path + ".txt");
            FileManager.getFile().saveToFile(path + ".txt", contents);
        } else {
            FileManager.getFile().openFile(path + ".txt");
            FileManager.getFile().saveToFile(path + ".txt", contents);
        }
    }
}
