package com.company.bank.main;


import com.company.bank.controllers.BankService;


public class Main {

    public static void main(String[] args) {
        BankService bankController = BankService.getInstance();
        do {
        } while (bankController.bankMenu());
    }
}
