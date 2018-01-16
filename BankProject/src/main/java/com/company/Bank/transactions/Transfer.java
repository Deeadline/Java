package com.company.bank.transactions;

import java.io.IOException;

public interface Transfer {
    void execute() throws IOException, RuntimeException;
}
