package main.java;


import com.company.Bank.controllers.BankController;
import org.apache.log4j.xml.DOMConfigurator;


public class Main {

    public static void main(String[] args) {
        DOMConfigurator.configure("E:\\Repozytoria\\Java\\BankProject\\src\\main\\resources\\log4j.xml");
        BankController bankController = BankController.getInstance();
            do {

            } while (bankController.bankMenu());
        /*Klasa transfer, po której dziedziczy Wpłac, wypłać, wypłać międzynarodowo, Zamiast println dać log4j, zredukować liczbę ifów i forów.*/
    }
}
