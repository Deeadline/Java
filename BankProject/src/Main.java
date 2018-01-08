import com.company.Bank.BankProvider;
import com.company.Bank.domain.Bank;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Person;
import com.company.Bank.domain.Swift;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BankProvider bankProvider = BankProvider.getBankProviderInstance();
        Scanner scanner = new Scanner(System.in);
        Person person;
        String name, surname, birthDate;
        String paymentTitle, from, to;
        int userChoice, firstChoice, secondChoice, thirdChoice, fourthChoice, money;
        boolean type = true;

        do {
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
            switch (userChoice) {
                case 0:
                    System.out.println("Which bank do you want create? ");
                    for (Swift swift : Swift.values()) {
                        System.out.println(swift.ordinal() + ". " + swift);
                    }
                    firstChoice = scanner.nextInt();
                    for (Swift swift : Swift.values()) {
                        if (swift.ordinal() == firstChoice) {
                            bankProvider.createNewBank(swift);
                            break;
                        }
                    }
                    break;
                case 1:
                    System.out.println("Enter your name: ");
                    name = scanner.next();
                    System.out.println("Enter your surname: ");
                    surname = scanner.next();
                    System.out.println("Enter your birthdate: ");
                    birthDate = scanner.next();
                    person = new Person(name, surname, birthDate);
                    bankProvider.addUser(person);
                    break;
                case 2:
                    if (bankProvider.getUsers().isEmpty()) {
                        break;
                    } else {
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
                        bankProvider.addAccount(bankProvider.getUsers().get(secondChoice), bankProvider.getAllBanks().get(firstChoice).getSwiftNumber());
                    }
                    break;
                case 3:
                    int i = 0;
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
                    paymentTitle = "Payment from " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber() + " to " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber();
                    bankProvider.withdraw(from, to, money, paymentTitle);
                    break;
                case 4:
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
                    paymentTitle = "Payment to " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
                    to = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(secondChoice).getAccountNumber();
                    bankProvider.deposit(to, money, paymentTitle);
                    break;
                case 5:
                    bankProvider.readBank();
                    break;
                case 6:
                    bankProvider.readClients();
                    break;
                case 7:
                    bankProvider.readAccounts();
                    break;
                case 8:
                    bankProvider.readHistory();
                    break;
                case 9:
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
                    paymentTitle = "Payment from " + bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber() + " to " + bankProvider.getAllBanks().get(secondChoice).getBankAccountList().get(fourthChoice).getAccountNumber();
                    from = bankProvider.getAllBanks().get(firstChoice).getBankAccountList().get(thirdChoice).getAccountNumber();
                    to = bankProvider.getAllBanks().get(secondChoice).getBankAccountList().get(fourthChoice).getAccountNumber();
                    bankProvider.withdraw(from, to, money, paymentTitle);
                    break;
                case 10:
                    type = false;
                    break;
                default:
                    System.out.println("Wrong choice.");
                    break;
            }
        } while (type);
    }
}
