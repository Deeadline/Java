package main.java.com.company.Bank.controllers;


import main.java.com.company.Bank.domain.*;
import main.java.com.company.Bank.provider.BankProvider;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Scanner;


public class BankController {
    private static final Logger logger = Logger.getLogger(BankController.class);
    private static BankController instance;
    private final BankProvider bankProvider = BankProvider.getBankProviderInstance();
    private int firstChoice;
    private int secondChoice;
    private int thirdChoice;
    private int fourthChoice;
    private int money;
    private int i;
    private int userChoice;
    private final Scanner scanner = new Scanner(System.in);
    private final Payment payment = new Payment();
    private BankAccount from;
    private BankAccount to;
    private String surname;
    private String title;
    private Swift swiftNumber1;
    private Swift swiftNumber2;

    private BankController() {
    }

    public static BankController getInstance() {
        if (instance == null) {
            instance = new BankController();
        }
        return instance;
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
            userChoice = scanner.nextInt();
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
        firstChoice = scanner.nextInt();
        for (Swift swift : Swift.values()) {
            if (swift.ordinal() == firstChoice) {
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
            surname = scanner.next();
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
            firstChoice = scanner.nextInt();
            logger.info("Which person do you want to create an account? ");
            i = 0;
            for (Person user : bankProvider.getUsers()) {
                logger.info(i++ + ". " + user.getName() + " " + user.getSurname());
            }
            secondChoice = scanner.nextInt();
            if (bankProvider.addAccount(bankProvider.getUsers().get(secondChoice), bankProvider.getAllBanks().get(firstChoice).getSwiftNumber()))
                logger.info("An account has been created.");
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void cashWithdrawer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            i = 0;
            System.out.println("Which bank account do you want use? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            firstChoice = scanner.nextInt();
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want withdraw? ");
            secondChoice = scanner.nextInt();
            logger.info("How much money do you want withdraw? ");
            money = scanner.nextInt();
            swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
            from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice);
            to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice);
            payment.setTitle("Withdraw from " + swiftNumber1.toString() + " :\n" + from.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.cashWithdrawer(from, money, payment, swiftNumber1);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void transferWithdrawer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            i = 0;
            System.out.println("Which bank account do you want use? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            firstChoice = scanner.nextInt();
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want transfer? ");
            secondChoice = scanner.nextInt();
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want transfer?");
            thirdChoice = scanner.nextInt();
            logger.info("How much money do you want transfer? ");
            money = scanner.nextInt();
            logger.info("Please provide title: ");
            title = scanner.nextLine();
            swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
            from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice);
            to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice);
            payment.setTitle(title + "\nwithdraw from " + swiftNumber1.toString() + " : \n" + from.getAccountNumber() + " to: \n" + to.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.transferWithdrawer(from, to, money, payment, swiftNumber1);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void deposit() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            i = 0;
            logger.info("Which bank account do you want use? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            firstChoice = scanner.nextInt();
            i = 0;

            for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("To which account do you want deposit? ");
            secondChoice = scanner.nextInt();
            logger.info("How much money do you want deposit? ");
            money = scanner.nextInt();
            to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice);
            swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
            payment.setTitle("Deposit to " + swiftNumber1.toString() + " : " + to.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.deposit(to, money, payment, swiftNumber1);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void internationalTransfer() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            i = 0;
            System.out.println("Which bank account do you want use to Withdraw? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            firstChoice = scanner.nextInt();
            logger.info("Which bank account do you want use to Deposit? ");
            i = 0;
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            secondChoice = scanner.nextInt();

            logger.info(bankProvider.getAllBanks().get(firstChoice).getSwiftNumber().toString() + ":");
            i = 0;
            for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            logger.info("Whereof account do you want Withdraw? ");
            thirdChoice = scanner.nextInt();
            i = 0;
            logger.info(bankProvider.getAllBanks().get(secondChoice).getSwiftNumber().toString() + ":");
            for (BankAccount account : bankProvider.getAllBanks().get(secondChoice).getBankAccountList()) {
                logger.info(i++ + ". " + account.getAccountNumber());
            }
            System.out.println("To which account do you want Deposit? ");
            fourthChoice = scanner.nextInt();
            logger.info("How much money do you want Withdraw? ");
            money = scanner.nextInt();
            logger.info("Please provide title: ");
            title = scanner.nextLine();
            from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice);
            to = bankProvider.getAllBanks().get(secondChoice).getBankAccountList().get(fourthChoice);
            swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
            swiftNumber2 = bankProvider.getAllBanks().get(secondChoice).getSwiftNumber();
            payment.setTitle(title + "\nTransfer from " + swiftNumber1.toString() + " : " + from.getAccountNumber() + " to " + "\n" + swiftNumber2.toString() + " : " + to.getAccountNumber() + "\n cash: " + money + " \nDate: " + LocalDate.now());
            bankProvider.internationalTransfer(swiftNumber1, swiftNumber2, payment, money, from, to);
        } catch (Exception ex) {
            logger.error("Sorry, something wrong: ", ex);
        }
    }

    private void bankDetails() {
        if (bankProvider.getAllBanks().isEmpty())
            throw new NullPointerException("You don't have banks!");
        try {
            i = 0;
            logger.info("Which bank do you want to view? ");
            for (Bank bank : bankProvider.getAllBanks()) {
                logger.info(i++ + ". " + bank.getSwiftNumber().toString());
            }
            firstChoice = scanner.nextInt();
            swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
            for (String content : bankProvider.readBankHistory(swiftNumber1.toString())) {
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
            i = 0;
            for (Person user : bankProvider.getUsers()) {
                logger.info(i++ + ". " + user.getName() + " " + user.getSurname());
            }
            firstChoice = scanner.nextInt();
            surname = bankProvider.getUsers().get(firstChoice).getSurname();
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
            i = 0;
            logger.info("Which account do you want to view?");
            for (Bank bank : bankProvider.getAllBanks()) {
                System.out.println(i++ + ". " + bank.getSwiftNumber());
                int j = 0;
                for (BankAccount bankAccount : bank.getBankAccountList()) {
                    logger.info(j++ + ". " + bankAccount.getAccountNumber());
                }
            }
            logger.info("Bank: ");
            firstChoice = scanner.nextInt();
            logger.info("Account: ");
            secondChoice = scanner.nextInt();
            String accountNumber = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
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
