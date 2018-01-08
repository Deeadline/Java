import com.company.Bank.BankController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BankController bankController = BankController.getInstance();
        do {

        } while (bankController.bankMenu());
    }
}
