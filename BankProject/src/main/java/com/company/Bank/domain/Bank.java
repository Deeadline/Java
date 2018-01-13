package com.company.bank.domain;

import com.company.bank.provider.BankProvider;

import java.util.*;

public class Bank {
    private final Swift swiftNumber;
    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final List<Person> personList = new ArrayList<>();
    private final List<Payment> paymentsList = new ArrayList<>();

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

    public void addPayments(Payment paymentTitle) {
        paymentsList.add(paymentTitle);
    }

    @Override
    public String toString() {
        return "bank{" +
                "swiftNumber=" + swiftNumber +
                ", bankAccountList=" + bankAccountList +
                ", personList=" + personList +
                ", paymentsList=" + paymentsList +
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
