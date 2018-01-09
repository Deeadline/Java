package main.java;


import main.java.com.company.Bank.controllers.BankController;
import org.apache.log4j.PropertyConfigurator;


public class Main {

    public static void main(String[] args) {
        PropertyConfigurator.configure("E:\\Repozytoria\\Java\\BankProject\\src\\main\\resources\\log4j.properties");
        BankController bankController = BankController.getInstance();
            do {

            } while (bankController.bankMenu());
        /*Klasa transfer, po której dziedziczy Wpłac, wypłać, wypłać międzynarodowo, Zamiast println dać log4j, zredukować liczbę ifów i forów.*/
    }
}
