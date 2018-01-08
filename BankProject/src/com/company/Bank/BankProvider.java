package com.company.Bank;

import com.company.Bank.domain.Bank;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Person;
import com.company.Bank.domain.Swift;

import java.io.IOException;
import java.util.*;

public class BankProvider {
    private static BankProvider bankProviderInstance;
    private static final List<Person> users = new ArrayList<>();
    private static final List<Bank> bankList = new ArrayList<>();
    private static final FileManager file = new FileManager();

    private BankProvider() {
    }

    public static BankProvider getBankProviderInstance() {
        if (bankProviderInstance == null) {
            bankProviderInstance = new BankProvider();
        }
        return bankProviderInstance;
    }

    public boolean createNewBank(Swift swiftNumber) {
        if (getAllBanks().isEmpty()) {
            bankList.add(new Bank(swiftNumber));
        } else if (getBank(swiftNumber).getSwiftNumber() == swiftNumber)
            return false;
        return true;
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

    public void addUser(Person user) throws IOException {
        if (!file.isFileExist(user.getSurname() + ".txt"))
            file.openFile(user.getSurname() + ".txt");
        file.saveToFile(user.getSurname() + ".txt", user.toString());
        users.add(user);
    }


    public Boolean addAccount(Person accountUser, Swift swiftNumber) throws IOException {
        String numbers = new Random().ints().boxed().filter(i -> i >= 0 && i <= 9).limit(18).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        System.out.println(numbers);

        BankAccount account = new BankAccount(numbers);
        accountUser.addAccount(account);
        getBank(swiftNumber).addAccount(account);
        getBank(swiftNumber).addPerson(accountUser);

        updateHistory(swiftNumber.toString() + ".txt", String.valueOf(bankList));
        updateHistory(accountUser.getSurname() + ".txt", users.toString());
        if (!file.isFileExist(account.getAccountNumber() + ".txt"))
            file.openFile(account.getAccountNumber() + ".txt");
        file.saveToFile(account.getAccountNumber() + ".txt", account.toString());
        return true;
    }

    public void withdraw(String accountNumberFrom, String accountNumberTo, int money, String paymentTitle, Swift swiftBankNumber1, Swift swiftBankNumber2) throws IOException {
        for (Person user : users) {
            if (user.getAccount(accountNumberFrom) == null) {
                break;
            } else {
                BankAccount firstAccount = user.getAccount(accountNumberFrom);
                if (firstAccount.getAccountBalance() > 0) {
                    firstAccount.withdraw(money);
                    deposit(accountNumberTo, money, paymentTitle, swiftBankNumber2);
                    if (!file.isFileExist("Payments.txt"))
                        file.openFile("Payments.txt");
                    file.saveToFile("Payments.txt", "Title: " + paymentTitle + "\n");
                    user.addPayment("Title: " + paymentTitle + "\n");
                    getBank(swiftBankNumber1).addPayments(paymentTitle);
                    updateHistory(accountNumberFrom, firstAccount.toString());
                    updateHistory(user.getSurname(), users.toString());
                    updateHistory(swiftBankNumber1.toString(), getBank(swiftBankNumber1).getBankAccountList().toString());
                    return;
                }
            }
        }
    }

    public void deposit(String accountNumber, int money, String paymentTitle, Swift swiftNumber) throws IOException {
        for (Person user : users) {
            if (user.getAccount(accountNumber) == null) {
                break;
            } else {
                int value = user.getAccount(accountNumber).deposit(money);
                if (!file.isFileExist("Payments.txt"))
                    file.openFile("Payments.txt");
                file.saveToFile("Payments.txt", "Title: " + paymentTitle + "\n");
                user.addPayment("Title: " + paymentTitle + "\n");
                getBank(swiftNumber).addPayments(paymentTitle);
                updateHistory(accountNumber, String.valueOf(value));
                updateHistory(user.getSurname(), users.toString());
                updateHistory(swiftNumber.toString(), getBank(swiftNumber).getBankAccountList().toString());
                return;
            }
        }
    }

    public List<String> readPaymentsHistory() throws IOException {
        return file.readFromFile("Payments.txt");
    }

    public List<String> readClientHistory(String surname) throws IOException {
        return file.readFromFile(surname + ".txt");
    }

    public List<String> readAccountHistory(String accountNumber) throws IOException {
        return file.readFromFile(accountNumber + ".txt");
    }

    public List<String> readBankHistory(String swiftNumber) throws IOException {
        return file.readFromFile(swiftNumber + ".txt");
    }

    private void updateHistory(String path, String contents) throws IOException {
        if (file.isFileExist(path + ".txt")) {
            file.removeFile(path + ".txt");
            file.openFile(path + ".txt");
            file.saveToFile(path + ".txt", contents);
        } else {
            file.openFile(path + ".txt");
            file.saveToFile(path + ".txt", contents);
        }
    }

}
