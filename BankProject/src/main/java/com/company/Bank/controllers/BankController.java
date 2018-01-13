package com.company.bank.controllers;

import com.company.bank.domain.Bank;
import com.company.bank.domain.BankAccount;
import com.company.bank.domain.Payment;
import com.company.bank.domain.Person;
import com.company.bank.domain.Swift;
import com.company.bank.provider.BankProvider;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.time.LocalDate;
import java.util.Scanner;


public class BankController {
    private static final Logger logger = Logger.getLogger(BankController.class);
    private static BankController instance;
    private final BankProvider bankProvider = BankProvider.getBankProviderInstance();
    private final Scanner scanner = new Scanner(System.in);

    private BankController() {
    }

    public static BankController getInstance() {
        if (instance == null) {
            instance = new BankController();
        }
        return instance;
    }

    static{
        DOMConfigurator.configure("D:\\Java\\BankProject\\src\\main\\resources\\log4j.xml");
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
            if (userChoice == 11)
                return false;
            doSomething(userChoice);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
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
        try {
            logger.info("Enter your name: ");
            String name = scanner.next();
            logger.info("Enter your surname: ");
            String surname = scanner.next();
            logger.info("Enter your birthdate: ");
            String birthDate = scanner.next();
            Person person = new Person(name, surname, birthDate);
            bankProvider.addUser(person);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void createAccount() {
        try {
            int i = 0;
            logger.info("Which bank do you want to add an account? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            logger.info("Which person do you want to create an account? ");
            i = 0;
            for (Person user : bankProvider.getUsers()) {
                logger.info(i++ + ". " + user.getName() + " " + user.getSurname());
            }
            int personChoice = scanner.nextInt();
            if (bankProvider.addAccount(bankProvider.getUsers().get(personChoice), bankProvider.getAllBanks().get(bankChoice).getSwiftNumber()))
                logger.info("An account has been created.");
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void cashWithdrawer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int i=0;
            System.out.println("Which bank account do you want use? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want withdraw? ");
            int accountChoice = scanner.nextInt();
            logger.info("How much money do you want withdraw? ");
            int money = scanner.nextInt();
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            BankAccount withdrawFrom = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(accountChoice);
            Payment payment = new Payment("Withdraw from " + bankSwiftNumber.toString() + " :\n" + withdrawFrom.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.cashWithdrawer(withdrawFrom, money, payment, bankSwiftNumber);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void transferWithdrawer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int i = 0;
            System.out.println("Which bank account do you want use? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want transfer? ");
            int firstAccountChoice = scanner.nextInt();
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want transfer?");
            int secondAccountChoice = scanner.nextInt();
            logger.info("How much money do you want transfer? ");
            int money = scanner.nextInt();
            logger.info("Please provide title: ");
            String title = scanner.nextLine();
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            BankAccount transferFrom = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(firstAccountChoice);
            BankAccount transferTo = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(secondAccountChoice);
            Payment payment = new Payment(title + "\nwithdraw from " + bankSwiftNumber.toString() + " : \n" + transferFrom.getAccountNumber() + " to: \n" + transferTo.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.transferWithdrawer(transferFrom, transferTo, money, payment, bankSwiftNumber);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void deposit() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int i = 0;
            logger.info("Which bank account do you want use? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            i = 0;

            for (BankAccount account : bankProvider.getAllBanks().get(bankChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want deposit? ");
            int accountChoice = scanner.nextInt();
            logger.info("How much money do you want deposit? ");
            int money = scanner.nextInt();
            BankAccount depositTo = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(accountChoice);
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            Payment payment = new Payment("Deposit to " + bankSwiftNumber.toString() + " : " + depositTo.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.deposit(depositTo, money, payment, bankSwiftNumber);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void internationalTransfer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int i = 0;
            System.out.println("Which bank account do you want use to Withdraw? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int firstBankChoice = scanner.nextInt();
            logger.info("Which bank account do you want use to Deposit? ");
            i = 0;
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int secondBankChoice = scanner.nextInt();

            logger.info(bankProvider.getAllBanks().get(firstBankChoice).getSwiftNumber().toString() + ":");
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(firstBankChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want Withdraw? ");
            int firstAccountChoice = scanner.nextInt();
            i = 0;
            logger.info(bankProvider.getAllBanks().get(secondBankChoice).getSwiftNumber().toString() + ":");
            for (BankAccount account : bankProvider.getAllBanks().get(secondBankChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            System.out.println("To which account do you want Deposit? ");
            int secondAccountChoice = scanner.nextInt();
            logger.info("How much money do you want Withdraw? ");
            int money = scanner.nextInt();
            logger.info("Please provide title: ");
            String title = scanner.nextLine();
            BankAccount transferFrom = bankProvider.getAllBanks().get(firstBankChoice).getBankAccountList().get(firstAccountChoice);
            BankAccount transferTo = bankProvider.getAllBanks().get(secondBankChoice).getBankAccountList().get(secondAccountChoice);
            Swift firstBankSwiftNumber = bankProvider.getAllBanks().get(firstBankChoice).getSwiftNumber();
            Swift secondBankSwiftNumber = bankProvider.getAllBanks().get(secondBankChoice).getSwiftNumber();
            Payment payment = new Payment(title + "\nTransfer from " + firstBankSwiftNumber.toString() + " : " + transferFrom.getAccountNumber() + " to " + "\n" + secondBankSwiftNumber.toString() + " : " + transferTo.getAccountNumber() + "\n cash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.internationalTransfer(firstBankSwiftNumber, secondBankSwiftNumber, payment, money, transferFrom, transferTo);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void bankDetails() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int i = 0;
            logger.info("Which bank do you want to view? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            int bankChoice = scanner.nextInt();
            Swift bankSwiftNumber = bankProvider.getAllBanks().get(bankChoice).getSwiftNumber();
            for (String content : bankProvider.readBankHistory(bankSwiftNumber.toString())) {
                logger.info(content);
            }
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void userDetails() {
        if (bankProvider.getUsers().isEmpty())
            throw new NullPointerException("You don't have persons!");
        try {
            logger.info("Which person do you want to view? ");
            int i = 0;
            for (Person user : bankProvider.getUsers()) {
                logger.info(i++ + ". " + user.getName() + " " + user.getSurname());
            }
            int userChoice = scanner.nextInt();
            String surname = bankProvider.getUsers().get(userChoice).getSurname();
            logger.info("Person view: ");
            for (String content : bankProvider.readClientHistory(surname)) {
                logger.info(content);
            }
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void accountDetails() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            int i = 0;
            logger.info("Which account do you want to view?");
            for (Bank bank : bankProvider.getAllBanks()) {
                System.out.println(i++ + ". " + bank.getSwiftNumber());
                int j = 0;
                for (BankAccount bankAccount : bank.getBankAccountList()) {
                    logger.info(j++ + ". " + bankAccount.getAccountNumber());
                }
            }
            logger.info("bank: ");
            int bankChoice = scanner.nextInt();
            logger.info("Account: ");
            int accountChoice = scanner.nextInt();
            String accountNumber = bankProvider.getAllBanks().get(bankChoice).getBankAccountList().get(accountChoice).getAccountNumber();
            logger.info("Account view: ");
            for (String content : bankProvider.readAccountHistory(accountNumber)) {
                System.out.println(content);
            }
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void paymentDetails() {
        try {
            System.out.println("Transfers view: ");
            for (String content : bankProvider.readPaymentsHistory()) {
                logger.info(content);
            }
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }
}
