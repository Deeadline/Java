package com.company.bank.domain;

import com.company.bank.provider.BankProvider;
import com.company.bank.transactions.Transfer;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Bank implements Savable {
    private Swift swiftNumber;
    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final List<Person> personList = new ArrayList<>();
    private final List<Transfer> paymentsList = new ArrayList<>();

    public Bank() {

    }

    public Bank(Swift swiftNumber) {
        if (BankProvider.getBankProviderInstance().getBank(swiftNumber) != null)
            throw new IllegalArgumentException("There cannot exists 2 banks with one Swift number");
        this.swiftNumber = swiftNumber;
    }

    public Swift getSwiftNumber() {
        return swiftNumber;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void addPayments(Transfer transfer) {
        paymentsList.add(transfer);
    }

    @Override
    public void load(String content) {
        swiftNumber = Swift.valueOf(content);
    }

    @Override
    public String save() {
        return swiftNumber.name();
    }

    @Override
    public String toString() {
        return "bank{" +
                "swiftNumber=" + swiftNumber.name() +
                ",\r\n bankAccountList=" + bankAccountList +
                ",\r\n personList=" + personList +
                ",\r\n paymentsList=" + paymentsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Bank)) return false;
        Bank bank = (Bank) o;
        return Objects.equals(swiftNumber, bank.swiftNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(swiftNumber);
    }
}
