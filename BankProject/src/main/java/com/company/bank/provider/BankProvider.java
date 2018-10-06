package com.company.bank.provider;


import com.company.bank.controllers.FileManager;
import com.company.bank.domain.Bank;
import com.company.bank.domain.Swift;
import com.company.bank.domain.Person;
import com.company.bank.domain.BankAccount;
import com.company.bank.transactions.Deposit;
import com.company.bank.transactions.DomesticTransfer;
import com.company.bank.transactions.InternationalTransfer;
import com.company.bank.transactions.Withdraw;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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

    public List<String> readPaymentsHistory() throws IOException, RuntimeException {
        return FileManager.getInstance().readFromFile("Payments.txt");
    }

    public List<String> readClientHistory(String PESEL) throws IOException, RuntimeException {
        return FileManager.getInstance().readFromFile(PESEL + ".txt");
    }

    public List<String> readAccountHistory(String accountNumber) throws IOException, RuntimeException {
        return FileManager.getInstance().readFromFile(accountNumber + ".txt");
    }

    public List<String> readBankHistory(String swiftNumber) throws IOException, RuntimeException {
        return FileManager.getInstance().readFromFile(swiftNumber + ".txt");
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

    public void addUser(Person user) {
        users.add(user);
    }

    public Boolean addAccount(Person accountUser, Swift swiftNumber) throws IOException, RuntimeException {
        String numbers = getRandomNumbers();
        Bank bank = getBank(swiftNumber);

        if (checkIfBankAccountExists(bank, numbers)) {

            BankAccount account = new BankAccount(numbers, swiftNumber.toString(), accountUser.getPESEL());
            accountUser.addAccount(account);
            Objects.requireNonNull(bank).addAccount(account);
            bank.addPerson(accountUser);

            updateHistory(swiftNumber.toString(), bank.toString());
            updateHistory(accountUser.getPESEL(), users.toString());
            updateHistory(account.getAccountNumber(), account.toString());

            return true;
        }
        return false;
    }

    public void cashWithdraw(BankAccount account, int money, Swift swiftNumber) throws IOException, RuntimeException {
        Withdraw transfer = new Withdraw(account, swiftNumber, money);
        transfer.execute();
    }

    public void transferWithdraw(BankAccount[] accounts, Swift swiftNumber, int money, String paymentTitle) throws IOException, RuntimeException {
        DomesticTransfer transfer = new DomesticTransfer(accounts, swiftNumber, money, paymentTitle);
        transfer.execute();
    }

    public void internationalTransfer(BankAccount[] accounts, Swift[] swifts, String paymentTitle, int money) throws IOException, RuntimeException {
        InternationalTransfer transfer = new InternationalTransfer(accounts, swifts, money, paymentTitle);
        transfer.execute();
    }

    public void deposit(BankAccount account, int money, Swift swiftNumber) throws IOException, RuntimeException {
        Deposit depositTransfer = new Deposit(account, swiftNumber, money);
        depositTransfer.execute();
    }

    public void updateHistory(String path, String contents) throws IOException, RuntimeException {
        if (FileManager.getInstance().isFileExist(path + ".txt")) {
            FileManager.getInstance().removeFile(path + ".txt");
            FileManager.getInstance().openFile(path + ".txt");
            FileManager.getInstance().saveToFile(path + ".txt", contents);
        } else {
            FileManager.getInstance().openFile(path + ".txt");
            FileManager.getInstance().saveToFile(path + ".txt", contents);
        }
    }

    public void loadContentFromFiles() throws IOException, RuntimeException {
        loadBanks();
        loadAccounts();
        loadUsers();
    }

    private void loadBanks() throws IOException, RuntimeException {
        if (FileManager.getInstance().isFileExist("Banks.txt")) {
            for (String content : FileManager.getInstance().readFromFile("Banks.txt")) {
                Bank bank = new Bank();
                bank.load(content);
                bankList.add(bank);
            }
        }
    }

    private void loadAccounts() throws IOException, RuntimeException {
        if (FileManager.getInstance().isFileExist("Accounts.txt")) {
            for (String content : FileManager.getInstance().readFromFile("Accounts.txt")) {
                BankAccount account = new BankAccount();
                account.load(content);
                for (Person person : users) {
                    if (person.getPESEL().equals(account.getOwnerPESEL())) {
                        person.addAccount(account);
                    }
                }
                for (Bank bank : bankList) {
                    if (bank.getSwiftNumber().toString().equals(account.getBankName())) {
                        bank.addAccount(account);
                    }
                }
            }
        }
    }

    private void loadUsers() throws IOException, RuntimeException {
        if (FileManager.getInstance().isFileExist("People.txt")) {
            for (String content : FileManager.getInstance().readFromFile("People.txt")) {
                Person person = new Person();
                person.load(content);
                addUser(person);
            }
        }
    }

    private String getRandomNumbers() {
        return new Random().ints(0, 9).boxed()
                .filter(i -> i >= 0 && i <= 9).limit(18)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    private boolean checkIfBankAccountExists(Bank bank, String numbers) {
        if (Objects.requireNonNull(bank).getBankAccountList().size() != 0) {
            for (BankAccount bankAccount : bank.getBankAccountList()) {
                if (bankAccount.getAccountNumber().equals(numbers))
                    return false;
            }
        }
        return true;
    }
}
