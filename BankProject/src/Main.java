import com.company.Bank.controllers.BankController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BankController bankController = BankController.getInstance();
        do {

        } while (bankController.bankMenu());

        /*Klasa transfer, po której dziedziczy Wpłac, wypłać, wypłać międzynarodowo, Zamiast println dać log4j, zredukować liczbę ifów i forów.*/
    }
}
