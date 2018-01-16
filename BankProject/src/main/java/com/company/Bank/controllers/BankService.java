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
            bankProvider.loadBanks();
            bankProvider.loadAccounts();
            bankProvider.loadUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean bankMenu() {
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
        try {
            int userChoice = scanner.nextInt();
            if (userChoice == 11) {
                FileManager.getInstance().removeFile("People.txt");
                FileManager.getInstance().removeFile("Banks.txt");
                FileManager.getInstance().removeFile("Accounts.txt");
                FileManager.getInstance().openFile("People.txt");
                FileManager.getInstance().openFile("Banks.txt");
                FileManager.getInstance().openFile("Accounts.txt");
                for (Bank bank : BankProvider.getBankProviderInstance().getAllBanks()) {
                    FileManager.getInstance().saveToFile("Banks.txt", bank.save());
                    for (BankAccount account : bank.getBankAccountList()) {
                        FileManager.getInstance().saveToFile("Accounts.txt", account.save());
                    }
                }
                for (Person person : BankProvider.getBankProviderInstance().getUsers()) {
                    FileManager.getInstance().saveToFile("People.txt", person.save());
                }
                return false;
            }
            doSomething(userChoice);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
            System.exit(-1);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
            System.exit(-1);
        }
        return true;
    }

    private void doSomething(int userChoice) {
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
                cashWithdrawer();
                break;
            case 4:
                transferWithdrawer();
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
        logger.info("Which bank do you want create? ");
        for (Swift swift : Swift.values()) {
            logger.info(swift.ordinal() + ". " + swift);
        }
        int choice = scanner.nextInt();
        for (Swift swift : Swift.values()) {
            if (swift.ordinal() == choice) {
                if (bankProvider.createNewBank(swift)) {
                    logger.info("The bank has been created.");
                    break;
                }
            }
        }
    }

    private void createUser() {
        logger.info("Enter your name: ");
        String name = scanner.next();
        logger.info("Enter your surname: ");
        String surname = scanner.next();
        logger.info("Enter your PESEL");
        String PESEL = scanner.next();
        Person person = new Person(name, surname, PESEL);
        bankProvider.addUser(person);
    }

    private void createAccount() {
        try {
            int bankIterator = 0;
            logger.info("Which bank do you want to add an account? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            logger.info("Which person do you want to create an account? ");
            int userIterator = 0;
            for (Person user : bankProvider.getUsers()) {
                logger.info(userIterator++ + ". " + user.getName() + " " + user.getSurname());
            }
            int personChoice = scanner.nextInt();
            if (bankProvider.addAccount(bankProvider.getUsers().get(personChoice), bankProvider.getAllBanks().get(bankChoice).getSwiftNumber()))
                logger.info("An account has been created.");
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void cashWithdrawer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int bankIterator = 0;
            logger.info("Which bank do you want use to transfer? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            int accountIterator = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(accountIterator++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want to withdraw? ");
            int accountChoice = scanner.nextInt();
            logger.info("How much cash do you want to withdraw? ");
            int cash = scanner.nextInt();
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            BankAccount withdrawFrom = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(accountChoice);
            bankProvider.cashWithdrawer(withdrawFrom, cash, bankSwiftNumber);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void transferWithdrawer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int bankIterator = 0;
            logger.info("Which bank do you want use to transfer? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            int accountIterator = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(accountIterator++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want to transfer? ");
            int firstAccountChoice = scanner.nextInt();
            accountIterator = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(accountIterator++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want transfer to ?");
            int secondAccountChoice = scanner.nextInt();
            logger.info("How much money do you want transfer to? ");
            int money = scanner.nextInt();
            logger.info("Please provide title: ");
            String title = scanner.nextLine();
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            BankAccount transferFrom = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(firstAccountChoice);
            BankAccount transferTo = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(secondAccountChoice);
            bankProvider.transferWithdrawer(transferFrom, transferTo, money, title, bankSwiftNumber);
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
            int bankIterator = 0;
            logger.info("Which bank account do you want use to deposit? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            int accountIterator = 0;

            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(accountIterator++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want to deposit? ");
            int accountChoice = scanner.nextInt();
            logger.info("How much money do you want to deposit? ");
            int money = scanner.nextInt();
            BankAccount depositTo = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(accountChoice);
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            bankProvider.deposit(depositTo, money, bankSwiftNumber);
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
            int bankIterator = 0;
            logger.info("Which bank account do you want use to transfer? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int firstBankChoice = scanner.nextInt();
            logger.info("Which bank account do you want use to transfer to? ");
            bankIterator = 0;
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int secondBankChoice = scanner.nextInt();

            logger.info(bankProvider.getAllBanks().get(firstBankChoice).getSwiftNumber().toString() + ":");
            int accountIterator = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(firstBankChoice).getBankAccountList()) {
                logger.info(accountIterator++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want to transfer? ");
            int firstAccountChoice = scanner.nextInt();
            accountIterator = 0;
            logger.info(bankProvider.getAllBanks().get(secondBankChoice).getSwiftNumber().toString() + ":");
            for (BankAccount account : bankProvider.getAllBanks().get(secondBankChoice).getBankAccountList()) {
                logger.info(accountIterator++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want transfer to? ");
            int secondAccountChoice = scanner.nextInt();
            logger.info("How much money do you want to transfer? ");
            int money = scanner.nextInt();
            logger.info("Please provide title: ");
            String title = scanner.nextLine();
            BankAccount transferFrom = bankProvider.getAllBanks().get(firstBankChoice).getBankAccountList().get(firstAccountChoice);
            BankAccount transferTo = bankProvider.getAllBanks().get(secondBankChoice).getBankAccountList().get(secondAccountChoice);
            Swift firstBankSwiftNumber = bankProvider.getAllBanks().get(firstBankChoice).getSwiftNumber();
            Swift secondBankSwiftNumber = bankProvider.getAllBanks().get(secondBankChoice).getSwiftNumber();
            bankProvider.internationalTransfer(firstBankSwiftNumber, secondBankSwiftNumber, title, money, transferFrom, transferTo);
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
            int bankIterator = 0;
            logger.info("Which bank do you want to view? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            for (String content : bankProvider.readBankHistory(bankSwiftNumber.toString())) {
                logger.info(content);
            }
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
            logger.info("Which person do you want to view? ");
            int userIterator = 0;
            for (Person user : bankProvider.getUsers()) {
                logger.info(userIterator++ + ". " + user.getName() + " " + user.getSurname());
            }
            int userChoice = scanner.nextInt();
            String PESEL = bankProvider.getUsers().get(userChoice).getPESEL();
            logger.info("Person view: ");
            for (String content : bankProvider.readClientHistory(PESEL)) {
                logger.info(content);
            }
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
            int bankIterator = 0;
            logger.info("Which account do you want to view?");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(bankIterator++ + ". " + bank.getSwiftNumber());
                int accountIterator = 0;
                for (BankAccount bankAccount : bank.getBankAccountList()) {
                    logger.info(accountIterator++ + ". " + bankAccount.getAccountNumber());
                }
            }
            logger.info("bank: ");
            int bankChoice = scanner.nextInt();
            logger.info("Account: ");
            int accountChoice = scanner.nextInt();
            String accountNumber = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(accountChoice).getAccountNumber();
            logger.info("Account view: ");
            for (String content : bankProvider.readAccountHistory(accountNumber)) {
                logger.info(content);
            }
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }

    private void paymentDetails() {
        try {
            logger.info("Transfers view: ");
            for (String content : bankProvider.readPaymentsHistory()) {
                logger.info(content);
            }
        } catch (IOException ex) {
            logger.error("Sorry, something wrong on IN-OUT : ", ex);
        } catch (RuntimeException ex) {
            logger.error("Sorry, something wrong on Runtime: ", ex);
        }
    }
}
