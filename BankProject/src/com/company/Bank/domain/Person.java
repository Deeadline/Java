package com.company.Bank.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private String birthDate;
    private final List<BankAccount> bankAccountList = new ArrayList<>();


    public Person(String name, String surname, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    @Override
    public String toString() {
        return "Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", Birthdate=" + birthDate;
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

    public String getName() {

        return name;
    }

    public String getSurname() {
        return surname;
    }

    public BankAccount getAccount(String number) {
        for (BankAccount account : bankAccountList) {
            //System.out.println(account.getAccountNumber() + ";" + number);
            if (account.getAccountNumber().equals(number)) {
                return account;
            }
        }
        return null;
    }

    public void print() {
        System.out.println(this.toString());
        for (BankAccount account : bankAccountList) {
            System.out.println(account.toString());
        }
    }
}
