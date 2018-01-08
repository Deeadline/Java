package com.company.Bank;

import com.company.Bank.domain.Bank;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Person;
import com.company.Bank.domain.Swift;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class BankController {
    private static BankController instance;
    private final BankProvider bankProvider = BankProvider.getBankProviderInstance();
    private int firstChoice, secondChoice, thirdChoice, fourthChoice, userChoice, money, i;
    private Scanner scanner = new Scanner(System.in);
    private String paymentTitle, from, to, accountNumber;
    private Person person;
    private String name, surname, birthDate;
    private Swift swiftNumber1, swiftNumber2;

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
        System.out.println("3. Withdraw money");
        System.out.println("4. Deposit money");
        System.out.println("5. Show bank details");
        System.out.println("6. Show user details");
        System.out.println("7. Show account details");
        System.out.println("8. Show payments history");
        System.out.println("9. International transaction");
        System.out.println("10. To quit: \n");
        System.out.println("Enter your choice: ");
        userChoice = scanner.nextInt();
        if (userChoice == 10)
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
                withdraw();
                break;
            case 4:
                deposit();
                break;
            case 5:
                internationalTransfer();
                break;
            case 6:
                bankDetails();
                break;
            case 7:
                userDetails();
                break;
            case 8:
                accountDetails();
                break;
            case 9:
                paymentDetails();
                break;
            case 10:
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
        name = scanner.next();
        System.out.println("Enter your surname: ");
        surname = scanner.next();
        System.out.println("Enter your birthdate: ");
        birthDate = scanner.next();
        person = new Person(name, surname, birthDate);
        bankProvider.addUser(person);

    }

    private void createAccount() throws IOException {
        if (bankProvider.getUsers().isEmpty())
            throw new IllegalStateException("You dont have accounts!");
        else {
            int i = 0;
            System.out.println("Which bank do you want add an account? ");
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
    }

    private void withdraw() throws IOException {
        i = 0;
        System.out.println("Which bank account do you want use? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        i = 0;
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i + ". " + account.getAccountNumber());
            i++;
        }
        System.out.println("Whereof account do you want withdraw? ");
        secondChoice = scanner.nextInt();
        System.out.println("To which account do you want deposit? ");
        i = 0;
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i + ". " + account.getAccountNumber());
            i++;
        }
        thirdChoice = scanner.nextInt();
        System.out.println("How much money do you want withdraw? ");
        money = scanner.nextInt();
        from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
        to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        swiftNumber2 = bankProvider.getAllBanks().get(secondChoice).getSwiftNumber();
        paymentTitle = "Payment from " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber() + " to " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber() + " cash: " + money + " Date: " + LocalDate.now();
        bankProvider.withdraw(from, to, money, paymentTitle, swiftNumber1, swiftNumber2);
    }

    private void deposit() throws IOException {
        i = 0;
        System.out.println("Which bank account do you want use? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        i = 0;
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i + ". " + account.getAccountNumber());
            i++;
        }
        System.out.println("To which account do you want deposit? ");
        secondChoice = scanner.nextInt();
        System.out.println("How much money do you want deposit? ");
        money = scanner.nextInt();
        paymentTitle = "Payment to " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber() + " cash: " + money + " Date: " + LocalDate.now();
        to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        bankProvider.deposit(to, money, paymentTitle, swiftNumber1);
    }

    private void internationalTransfer() throws IOException {
        i = 0;
        System.out.println("Which bank account do you want use to withdraw? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        System.out.println("Which bank account do you want use to deposit? ");
        i = 0;
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        secondChoice = scanner.nextInt();
        System.out.println(bankProvider.getAllBanks().get(firstChoice).getSwiftNumber().toString() + ":");
        i = 0;
        for (BankAccount account : bankProvider.getAllBanks().get(firstChoice).getBankAccountList()) {
            System.out.println(i + ". " + account.getAccountNumber());
            i++;
        }
        System.out.println("Whereof account do you want withdraw? ");
        thirdChoice = scanner.nextInt();
        i = 0;
        System.out.println(bankProvider.getAllBanks().get(secondChoice).getSwiftNumber().toString() + ":");
        for (BankAccount account : bankProvider.getAllBanks().get(secondChoice).getBankAccountList()) {
            System.out.println(i + ". " + account.getAccountNumber());
            i++;
        }
        System.out.println("To which account do you want deposit? ");
        fourthChoice = scanner.nextInt();
        System.out.println("How much money do you want withdraw? ");
        money = scanner.nextInt();
        paymentTitle = "Payment from " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber() + " to " + bankProvider.getAllBanks().get(secondChoice).getBankAccountList().get(fourthChoice).getAccountNumber() + " cash: " + money + " Date: " + LocalDate.now();
        from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber();
        to = bankProvider.getAllBanks().get(secondChoice).getBankAccountList().get(fourthChoice).getAccountNumber();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        swiftNumber2 = bankProvider.getAllBanks().get(secondChoice).getSwiftNumber();
        bankProvider.withdraw(from, to, money, paymentTitle, swiftNumber1, swiftNumber2);
    }

    private void bankDetails() throws IOException {
        i = 0;
        System.out.println("Which bank do you want to view? ");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber().toString());
            i++;
        }
        firstChoice = scanner.nextInt();
        swiftNumber1 = bankProvider.getAllBanks().get(firstChoice).getSwiftNumber();
        for (String content : bankProvider.readBankHistory(swiftNumber1.toString())) {
            System.out.println(content);
        }
    }

    private void userDetails() throws IOException {
        System.out.println("Which person do you want to view? ");
        i = 0;
        for (Person user : bankProvider.getUsers()) {
            System.out.println(i + ". " + user.getName() + " " + user.getSurname());
            i++;
        }
        firstChoice = scanner.nextInt();
        surname = bankProvider.getUsers().get(firstChoice).getSurname();
        for (String content : bankProvider.readClientHistory(surname)) {
            System.out.println(content);
        }
    }

    private void accountDetails() throws IOException {
        System.out.println("Which account do you want to view?");
        for (Bank bank : bankProvider.getAllBanks()) {
            System.out.println(i + ". " + bank.getSwiftNumber());
            int j = 0;
            for (BankAccount bankAccount : bank.getBankAccountList()) {
                System.out.println(j + ". " + bankAccount.getAccountNumber());
            }
        }
        System.out.println("Bank: ");
        firstChoice = scanner.nextInt();
        System.out.println("Account: ");
        secondChoice = scanner.nextInt();
        accountNumber = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
        for (String content : bankProvider.readAccountHistory(accountNumber)) {
            System.out.println(content);
        }
    }

    private void paymentDetails() throws IOException {
        System.out.println("Payments view: ");
        for (String content : bankProvider.readPaymentsHistory()) {
            System.out.println(content);
        }
    }


}