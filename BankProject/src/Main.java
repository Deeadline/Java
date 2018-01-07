import com.company.Bank.BankProvider;
import com.company.Bank.domain.BankAccount;
import com.company.Bank.domain.Person;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BankProvider PLBank = BankProvider.getBankProviderInstance();
        Scanner scanner = new Scanner(System.in);
        Person person;
        String name;
        String surname;
        String birthDate;
        int userChoice, firstChoice, secondChoice, money;

        do {
            System.out.println("1. Create person");
            System.out.println("2. Create account");
            System.out.println("3. Withdraw money");
            System.out.println("4. Deposit money");
            System.out.println("5. Show bank details");
            System.out.println("6. Show user details");
            System.out.println("7. Show account details");
            System.out.println("8. Show payments history");
            System.out.println("0. to quit: \n");
            System.out.println("Enter your choice: ");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.println("Enter your name: ");
                    name = scanner.next();
                    System.out.println("Enter your surname: ");
                    surname = scanner.next();
                    System.out.println("Enter your birthdate: ");
                    birthDate = scanner.next();
                    person = new Person(name, surname, birthDate);
                    PLBank.addUser(person);
                    break;
                case 2:
                    if (PLBank.getUsers().isEmpty()) {
                        break;
                    } else {
                        int i = 0;
                        System.out.println("Which person do you want to create an account? ");
                        for (Person user : PLBank.getUsers()) {
                            System.out.println(i + ". " + user.getName() + " " + user.getSurname());
                            i++;
                        }
                        firstChoice = scanner.nextInt();
                        PLBank.addAccount(PLBank.getUsers().get(firstChoice));
                    }
                    break;
                case 3:
                    int i = 0;
                    for (BankAccount account : PLBank.getBank().getBankAccountList()) {
                        System.out.println(i + ". " + account.getAccountNumber());
                        i++;
                    }
                    System.out.println("Whereof account do you want withdraw? ");
                    firstChoice = scanner.nextInt();
                    System.out.println("To which account do you want deposit? ");
                    secondChoice = scanner.nextInt();
                    System.out.println("How much money do you want withdraw? ");
                    money = scanner.nextInt();
                    PLBank.withdraw(PLBank.getBank().getBankAccountList().get(firstChoice).getAccountNumber(), PLBank.getBank().getBankAccountList().get(secondChoice).getAccountNumber(), money, "Payment from " + PLBank.getBank().getBankAccountList().get(firstChoice).getAccountNumber() + " to " + PLBank.getBank().getBankAccountList().get(secondChoice).getAccountNumber());
                    break;
                case 4:
                    i = 0;
                    for (BankAccount account : PLBank.getBank().getBankAccountList()) {
                        System.out.println(i + ". " + account.getAccountNumber());
                        i++;
                    }
                    System.out.println("To which account do you want deposit? ");
                    firstChoice = scanner.nextInt();
                    System.out.println("How much money do you want withdraw? ");
                    money = scanner.nextInt();
                    PLBank.deposit(PLBank.getBank().getBankAccountList().get(firstChoice).getAccountNumber(), money, "Payment to " + PLBank.getBank().getBankAccountList().get(firstChoice).getAccountNumber());
                    break;
                case 5:
                    PLBank.readBank();
                    break;
                case 6:
                    PLBank.readClients();
                    break;
                case 7:
                    PLBank.readAccounts();
                    break;
                case 8:
                    PLBank.readHistory();
                    break;
                 default:
                    System.out.println("Wrong choice.");
                    break;
            }
        } while (userChoice != 0);
    }
}
