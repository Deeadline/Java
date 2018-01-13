package main.java;


import com.company.bank.controllers.BankController;


public class Main {

    public static void main(String[] args) {
        BankController bankController = BankController.getInstance();
        do {
        } while (bankController.bankMenu());
    }
}
