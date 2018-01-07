package com.company.Bank;

import com.company.Bank.domain.Bank;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankProvider {
    private static BankProvider bankProviderInstance;
    private static final List<Person> users = new ArrayList<>();
    private static final Bank bank = Bank.getBankInstance();
    private static final FileManager file = new FileManager();

/*    static{
        if(file.isFileExist("Persons.txt")){
            try {
                Person person = null;
                file.readFromFileToClass("Persons.txt"), person);
                users.add(person);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    public Bank getBank() {
        return bank;
    }

    private BankProvider() {
    }

    public static BankProvider getBankProviderInstance() {
        if (bankProviderInstance == null) {
            bankProviderInstance = new BankProvider();
        }
        return bankProviderInstance;
    }

    public List<Person> getUsers() {
        return users;
    }

    public void addUser(Person user) throws IOException {
        if (!file.isFileExist("Persons.txt"))
            file.openFile("Persons.txt");
        file.saveToFile("Persons.txt", user.toString());
        users.add(user);
    }


    public BankAccount addAccount(Person accountUser) throws IOException {
        String numbers = new Random().ints().boxed().filter(i -> i >= 0 && i <= 9).limit(18).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        BankAccount account = new BankAccount(numbers);
        accountUser.addAccount(account);
        bank.addAccount(account);
        if (!file.isFileExist("Bank.txt")) {
            file.openFile("Bank.txt");
        } else {
            file.removeFile("Bank.txt");
        }
        file.saveToFile("Bank.txt", bank.toString());
        if (!file.isFileExist("Accounts.txt"))
            file.openFile("Accounts.txt");
        file.saveToFile("Accounts.txt", account.toString());
        return account;
    }

    public void withdraw(String accountNumberFrom, String accountNumberTo, int money, String paymentTitle) throws IOException {
        for (Person user : users) {
            if (user.getAccount(accountNumberFrom) == null) {
                break;
            } else {
                BankAccount firstAccount = user.getAccount(accountNumberFrom);
                if (firstAccount.getAccountBalance() > 0) {
                    int value = firstAccount.withdraw(money);
                    deposit(accountNumberTo, money, paymentTitle);
                    if (!file.isFileExist("Payments.txt"))
                        file.openFile("Payments.txt");
                    file.saveToFile("Payments.txt", "Title: " + paymentTitle + " cash: " + money + " Date: " + LocalDate.now() + "\n");
                    updateAccounts(accountNumberFrom, value);
                    return;
                }
            }
        }
    }

    public void deposit(String accountNumber, int money, String paymentTitle) throws IOException {
        for (Person user : users) {
            if (user.getAccount(accountNumber) == null) {
                break;
            } else {
                int value = user.getAccount(accountNumber).deposit(money);
                if (!file.isFileExist("Payments.txt"))
                    file.openFile("Payments.txt");
                file.saveToFile("Payments.txt", "Title: " + paymentTitle + " cash: " + money + " Date: " + LocalDate.now() + "\n");
                updateAccounts(accountNumber, value);
                return;
            }
        }
    }

    public void readHistory() throws IOException {
        file.readFromFile("Payments.txt");
    }

    public void readClients() throws IOException {
        file.readFromFile("Persons.txt");
    }

    public void readAccounts() throws IOException {
        file.readFromFile("Accounts.txt");
    }

    public void readBank() throws IOException {
        file.readFromFile("Bank.txt");
    }

    private void updateAccounts(String accountNumber, int balance) throws IOException {
        if (!file.isFileExist("Accounts.txt"))
            file.openFile("Accounts.txt");
        file.updateFile("Accounts.txt", balance, accountNumber);
    }

}
