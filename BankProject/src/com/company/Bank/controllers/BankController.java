package com.company.Bank.controllers;

import com.company.Bank.domain.*;
import com.company.Bank.provider.BankProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class BankController {
    private static BankController instance;
    private final BankProvider bankProvider = BankProvider.getBankProviderInstance();
    private int firstChoice;
    private int secondChoice;
    private int thirdChoice;
    private int fourthChoice;
    private int money;
    private int i;
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

    public boolean bankMenu() throws IOException {
        System.out.println("0. Create bank");
        System.out.println("1. Create person");
        System.out.println("2. Create account");
        System.out.println("3. Withdraw cash from account");
        System.out.println("4. Withdraw cash to other account");
        System.out.println("5. Deposit cash");
        System.out.println("6. International transaction");
        System.out.println("7. Show bank details");
        System.out.println("8. Show user details");
        System.out.println("9. Show account details");
        System.out.println("10. Show payments history");
        System.out.println("11. To quit: \n");
        System.out.println("Enter your choice: ");
        int userChoice = scanner.nextInt();
        if (userChoice == 11)
            return false;
        doSomething(userChoice);
        return true;
    }

    private void doSomething(int userChoice) throws IOException {
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
        System.out.println("Which bank do you want create? ");
        for (Swift swift : Swift.values()) {
            System.out.println(swift.ordinal() + ". " + swift);
        }
        firstChoice = scanner.nextInt();
        for (Swift swift : Swift.values()) {
            if (swift.ordinal() == firstChoice) {
                if (bankProvider.createNewBank(swift)) {
                    System.out.println("The bank has been created.");
                    break;
                }
            }
        }
    }

    private void createUser() throws IOException {
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your surname: ");
        surname = scanner.next();
        System.out.println("Enter your birthdate: ");
        String birthDate = scanner.next();
        Person person = new Person(name, surname, birthDate);
        bankProvider.addUser(person);
    }

    private void createAccount() throws IOException {
        if (bankProvider.getUsers().isEmpty())
            throw new IllegalArgumentException("You don't have persons!");
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        int i = 0;
        System.out.println("Which bank do you want to add an account? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        System.out.println("Which person do you want to create an account? ");
        i = 0;
        for (Person user : bankProvider.getUsers()) {
            System.out.println(i + ". " + user.getName() + " " + user.getSurname());
            i++;
        }
        secondChoice = scanner.nextInt();
        if (bankProvider.addAccount(bankProvider.getUsers().get(secondChoice), bankProvider.getAllBanks().get(firstChoice).getSwiftNumber()))
            System.out.println("An account has been created.");
    }

    private void cashWithdrawer() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        i = 0;
        System.out.println("Which bank account do you want use? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        i = 0;
        if (bankProvider.getAllBanks().get(firstChoice).getBankAccountList().isEmpty())
            throw new IllegalArgumentException("You don't have accounts!");
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i + ". " + account.getAccountNumber());
            i++;
        }
        System.out.println("Whereof account do you want withdraw? ");
        secondChoice = scanner.nextInt();
        System.out.println("How much money do you want withdraw? ");
        money = scanner.nextInt();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice);
        to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice);
        payment.setTitle("Withdraw from " + swiftNumber1.toString() + " :\n" + from.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
        bankProvider.cashWithdrawer(from, money, payment, swiftNumber1);
    }

    private void transferWithdrawer() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        i = 0;
        System.out.println("Which bank account do you want use? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        i = 0;
        if (bankProvider.getAllBanks().get(firstChoice).getBankAccountList().isEmpty())
            throw new IllegalArgumentException("You don't have accounts!");
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i++ + ". " + account.getAccountNumber());
        }
        System.out.println("Whereof account do you want transfer? ");
        secondChoice = scanner.nextInt();
        i = 0;
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i++ + ". " + account.getAccountNumber());
        }
        System.out.println("To which account do you want transfer?");
        thirdChoice = scanner.nextInt();
        System.out.println("How much money do you want transfer? ");
        money = scanner.nextInt();
        System.out.println("Please provide title: ");
        title = scanner.nextLine();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice);
        to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice);
        payment.setTitle(title + "\nwithdraw from " + swiftNumber1.toString() + " : \n" + from.getAccountNumber() + " to: \n" + to.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
        bankProvider.transferWithdrawer(from, to, money, payment, swiftNumber1);
    }

    private void deposit() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        i = 0;
        System.out.println("Which bank account do you want use? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i++ + ". " + bank.getSwiftNumber().toString());
        }
        firstChoice = scanner.nextInt();
        i = 0;
        if (bankProvider.getAllBanks().get(firstChoice).getBankAccountList().isEmpty())
            throw new IllegalArgumentException("You don't have accounts!");
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i++ + ". " + account.getAccountNumber());
        }
        System.out.println("To which account do you want deposit? ");
        secondChoice = scanner.nextInt();
        System.out.println("How much money do you want deposit? ");
        money = scanner.nextInt();
        to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice);
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        payment.setTitle("Deposit to " + swiftNumber1.toString() + " : " + to.getAccountNumber() + " \ncash: " + money + " \nDate: " + LocalDate.now());
        bankProvider.deposit(to, money, payment, swiftNumber1);
    }

    private void internationalTransfer() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        i = 0;
        System.out.println("Which bank account do you want use to Withdraw? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i++ + ". " + bank.getSwiftNumber().toString());
        }
        firstChoice = scanner.nextInt();
        System.out.println("Which bank account do you want use to Deposit? ");
        i = 0;
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i++ + ". " + bank.getSwiftNumber().toString());
        }
        secondChoice = scanner.nextInt();
        if (bankProvider.getAllBanks().get(firstChoice).getBankAccountList().isEmpty() || bankProvider.getAllBanks().get(secondChoice).getBankAccountList().isEmpty())
            throw new IllegalArgumentException("You don't have accounts!");
        System.out.println(bankProvider.getAllBanks().get(firstChoice).getSwiftNumber().toString() + ":");
        i = 0;
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i++ + ". " + account.getAccountNumber());
        }
        System.out.println("Whereof account do you want Withdraw? ");
        thirdChoice = scanner.nextInt();
        i = 0;
        System.out.println(bankProvider.getAllBanks().get(secondChoice).getSwiftNumber().toString() + ":");
        for (BankAccount account : bankProvider.getAllBanks().get(secondChoice).getBankAccountList()) {
            System.out.println(i++ + ". " + account.getAccountNumber());
        }
        System.out.println("To which account do you want Deposit? ");
        fourthChoice = scanner.nextInt();
        System.out.println("How much money do you want Withdraw? ");
        money = scanner.nextInt();
        System.out.println("Please provide title: ");
        title = scanner.nextLine();
        from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice);
        to = bankProvider.getAllBanks().get(secondChoice).getBankAccountList().get(fourthChoice);
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        swiftNumber2 = bankProvider.getAllBanks().get(secondChoice).getSwiftNumber();
        payment.setTitle(title + "\nTransfer from " + swiftNumber1.toString() + " : " + from.getAccountNumber() + " to " + "\n" + swiftNumber2.toString() + " : " + to.getAccountNumber() + "\n cash: " + money + " \nDate: " + LocalDate.now());
        bankProvider.internationalTransfer(swiftNumber1, swiftNumber2, payment, money, from, to);
    }

    private void bankDetails() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        i = 0;
        System.out.println("Which bank do you want to view? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i++ + ". " + bank.getSwiftNumber().toString());
        }
        firstChoice = scanner.nextInt();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        for (String content : bankProvider.readBankHistory(swiftNumber1.toString())) {
            System.out.println(content);
        }
    }

    private void userDetails() throws IOException {
        if (bankProvider.getUsers().isEmpty())
            throw new IllegalArgumentException("You don't have users!");
        System.out.println("Which person do you want to view? ");
        i = 0;
        for (Person user : bankProvider.getUsers()) {
            System.out.println(i++ + ". " + user.getName() + " " + user.getSurname());
        }
        firstChoice = scanner.nextInt();
        surname = bankProvider.getUsers().get(firstChoice).getSurname();
        System.out.println("Person view: ");
        for (String content : bankProvider.readClientHistory(surname)) {
            System.out.println(content);
        }
    }

    private void accountDetails() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        for (Bank bank : bankProvider.getAllBanks()) {
            if (bank.getBankAccountList().isEmpty())
                throw new IllegalArgumentException("You don't have accounts!");
        }
        i = 0;
        System.out.println("Which account do you want to view?");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i++ + ". " + bank.getSwiftNumber());
            int j = 0;
            for (BankAccount bankAccount : bank.getBankAccountList()) {
                System.out.println(j++ + ". " + bankAccount.getAccountNumber());
            }
        }
        System.out.println("Bank: ");
        firstChoice = scanner.nextInt();
        System.out.println("Account: ");
        secondChoice = scanner.nextInt();
        String accountNumber = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
        System.out.println("Account view: ");
        for (String content : bankProvider.readAccountHistory(accountNumber)) {
            System.out.println(content);
        }
    }

    private void paymentDetails() throws IOException {
        if (bankProvider.getAllBanks().isEmpty())
            throw new IllegalArgumentException("You don't have banks!");
        for (Bank bank : bankProvider.getAllBanks()) {
            if (bank.getBankAccountList().isEmpty())
                throw new IllegalArgumentException("You don't have accounts!");
        }
        if (bankProvider.getUsers().isEmpty())
            throw new IllegalArgumentException("You don't have persons!");

        System.out.println("Transfers view: ");
        for (String content : bankProvider.readPaymentsHistory()) {
            System.out.println(content);
        }
    }
}
