package com.company.bank.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private final String name;
    private final String surname;
    private final String birthDate;
    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final List<Payment> paymentsList = new ArrayList<>();

    public Person(String name, String surname, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<BankAccount> getAccount() {
        return bankAccountList;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public void addPayment(Payment payment) {
        paymentsList.add(payment);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", bankAccountList=" + bankAccountList +
                ", paymentsList=" + paymentsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(bankAccountList, person.bankAccountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, bankAccountList);
    }

}
