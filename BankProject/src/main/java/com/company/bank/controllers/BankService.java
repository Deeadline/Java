package com.company.bank.controllers;

import com.company.bank.domain.Bank;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Person;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.util.Scanner;


public class BankService {
    private static final Logger logger = Logger.getLogger(BankService.class);
    private static BankService instance;
    private final static BankProvider bankProvider = BankProvider.getBankProviderInstance();
    private final Scanner scanner = new Scanner(System.in);

    private BankService() {
    }

    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    static {
        DOMConfigurator.configure("src\\main\\resources\\log4j.xml");
        try {
            bankProvider.loadContentFromFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean bankMenu() {
        printMenu();
        try {
            int userChoice = scanner.nextInt();
            if (userChoice == 11) {
                cleanFiles();
                saveInformations();
                return false;
            }
            doActionFromUserChoice(userChoice);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
            System.exit(-1);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
            System.exit(-1);
        }
        return true;
    }

    private void doActionFromUserChoice(int userChoice) {
        switch (userChoice) {
            case 0:
                createBank();
                break;
            case 1:
                createUser();
                break;
            case 2:
                createAccount();
                break;
            case 3:
                cashWithdraw();
                break;
            case 4:
                transferWithdraw();
                break;
            case 5:
                deposit();
                break;
            case 6:
                internationalTransfer();
                break;
            case 7:
                bankDetails();
                break;
            case 8:
                userDetails();
                break;
            case 9:
                accountDetails();
                break;
            case 10:
                paymentDetails();
                break;
            default:
                break;
        }
    }

    private void createBank() {
        int swiftChoice = getBankSwiftFromUserChoice();
        for (Swift swift : Swift.values()) {
            if (swift.ordinal() == swiftChoice) {
                if (bankProvider.createNewBank(swift)) {
                    logger.info("The bank has been created.");
                    break;
                }
            }
        }
    }

    private void createUser() {
        String[] userData = getUserData();
        Person person = new Person(userData[0], userData[1], userData[2]);
        bankProvider.addUser(person);
    }

    private void createAccount() {
        try {
            Bank bank = getBankFromUserChoice();
            logger.info("Which person do you want to create an account? ");
            Person account = getAccountFromUserChoice();
            if (bankProvider.addAccount(account, bank.getSwiftNumber()))
                logger.info("An account has been created.");
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void cashWithdraw() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            Bank bank = getBankFromUserChoice();
            BankAccount bankAccount = getBankAccountFromUserChoice(bank);
            logger.info("How much cash do you want to withdraw? ");
            int cashToWithdraw = scanner.nextInt();
            Swift bankSwiftNumber = bank.getSwiftNumber();
            bankProvider.cashWithdraw(bankAccount, cashToWithdraw, bankSwiftNumber);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void transferWithdraw() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            Bank bank = getBankFromUserChoice();
            logger.info("BankAccount from: ");
            BankAccount transferFrom = getBankAccountFromUserChoice(bank);
            logger.info("BankAccount to: ");
            BankAccount transferTo = getBankAccountFromUserChoice(bank);

            logger.info("How much money do you want transfer to? ");
            int money = scanner.nextInt();

            logger.info("Please provide title: ");
            String title = scanner.nextLine();

            Swift bankSwiftNumber = bank.getSwiftNumber();

            BankAccount[] accounts = new BankAccount[] {transferFrom, transferTo};
            bankProvider.transferWithdraw(accounts, bankSwiftNumber, money, title);

        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void deposit() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            Bank bank = getBankFromUserChoice();
            BankAccount bankAccount = getBankAccountFromUserChoice(bank);

            logger.info("How much money do you want to deposit? ");
            int money = scanner.nextInt();

            Swift bankSwiftNumber = bank.getSwiftNumber();
            bankProvider.deposit(bankAccount, money, bankSwiftNumber);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void internationalTransfer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            Bank firstBank = getBankFromUserChoice();
            Bank secondBank = getBankFromUserChoice();
            BankAccount transferFrom = getBankAccountFromUserChoice(firstBank);
            BankAccount transferTo = getBankAccountFromUserChoice(secondBank);


            logger.info("How much money do you want to transfer? ");
            int money = scanner.nextInt();
            logger.info("Please provide title: ");
            String title = scanner.nextLine();

            Swift firstBankSwiftNumber = firstBank.getSwiftNumber();
            Swift secondBankSwiftNumber = secondBank.getSwiftNumber();

            Swift[] swifts = new Swift[] {firstBankSwiftNumber, secondBankSwiftNumber};
            BankAccount[] accounts = new BankAccount[] {transferFrom, transferTo};

            bankProvider.internationalTransfer(accounts, swifts, title, money);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void bankDetails() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            Bank bank = getBankFromUserChoice();

            readBankContent(bank.getSwiftNumber().toString());

        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void userDetails() {
        if (bankProvider.getUsers().isEmpty())
            throw new NullPointerException("You don't have persons!");
        try {

            Person user = getAccountFromUserChoice();

            readUserContent(user.getPESEL());

        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void accountDetails() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {

            Bank bank = getBankFromUserChoice();
            BankAccount bankAccount = getBankAccountFromUserChoice(bank);

            readAccountContent(bankAccount.getAccountNumber());
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void paymentDetails() {
        try {
            readPaymentContent();
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void printMenu() {
        logger.info("0. Create bank");
        logger.info("1. Create person");
        logger.info("2. Create account");
        logger.info("3. Withdraw cash from account");
        logger.info("4. Withdraw cash to other account");
        logger.info("5. Deposit cash");
        logger.info("6. International transaction");
        logger.info("7. Show bank details");
        logger.info("8. Show user details");
        logger.info("9. Show account details");
        logger.info("10. Show payments history");
        logger.info("11. To quit: \n");
        logger.info("Enter your choice: ");
    }

    private void cleanFiles() throws IOException {
        FileManager.getInstance().removeFile("People.txt");
        FileManager.getInstance().removeFile("Banks.txt");
        FileManager.getInstance().removeFile("Accounts.txt");
        FileManager.getInstance().openFile("People.txt");
        FileManager.getInstance().openFile("Banks.txt");
        FileManager.getInstance().openFile("Accounts.txt");
    }

    private void saveInformations() throws IOException {
        for (Bank bank : BankProvider.getBankProviderInstance().getAllBanks()) {
            FileManager.getInstance().saveToFile("Banks.txt", bank.save());
            for (BankAccount account : bank.getBankAccountList()) {
                FileManager.getInstance().saveToFile("Accounts.txt", account.save());
            }
        }
        for (Person person : BankProvider.getBankProviderInstance().getUsers()) {
            FileManager.getInstance().saveToFile("People.txt", person.save());
        }
    }

    private void readBankContent(String bankSwiftNumber) throws IOException {
        for (String content : bankProvider.readBankHistory(bankSwiftNumber)) {
            logger.info(content);
        }
    }

    private void readUserContent(String PESEL) throws IOException {
        logger.info("Person view: ");
        for (String content : bankProvider.readClientHistory(PESEL)) {
            logger.info(content);
        }
    }

    private void readAccountContent(String accountNumber) throws IOException {
        logger.info("Account view: ");
        for (String content : bankProvider.readAccountHistory(accountNumber)) {
            logger.info(content);
        }
    }

    private void readPaymentContent() throws IOException {
        logger.info("Transfers view: ");
        for (String content : bankProvider.readPaymentsHistory()) {
            logger.info(content);
        }
    }

    private Bank getBankFromUserChoice() {
        int bankIterator = 0;
        logger.info("Which bank do you want use to transfer? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
        }
        int bankChoice = scanner.nextInt();
        return bankProvider.getAllBanks().get(bankChoice);
    }

    private Person getAccountFromUserChoice() {
        int userIterator = 0;
        for (Person user : bankProvider.getUsers()) {
            logger.info(userIterator++ + ". " + user.getName() + " " + user.getSurname());
        }
        int personChoice = scanner.nextInt();
        return bankProvider.getUsers().get(personChoice);
    }

    private int getBankSwiftFromUserChoice() {
        logger.info("Which bank do you want create? ");
        for (Swift swift : Swift.values()) {
            logger.info(swift.ordinal() + ". " + swift);
        }
        return scanner.nextInt();
    }

    private BankAccount getBankAccountFromUserChoice(Bank bank) {
        int accountIterator = 0;
        for (BankAccount account : bank.getBankAccountList()) {
            logger.info(accountIterator++ + ". " + account.getAccountNumber());
        }
        logger.info("Which account do you need? ");
        int accountChoice = scanner.nextInt();
        return bank.getBankAccountList().get(accountChoice);
    }

    private String[] getUserData() {
        logger.info("Enter your name: ");
        String name = scanner.next();
        logger.info("Enter your surname: ");
        String surname = scanner.next();
        logger.info("Enter your PESEL");
        String PESEL = scanner.next();
        return new String[]{name, surname, PESEL};
    }
}
