package com.company.bank.domain;

import com.company.bank.provider.BankProvider;
import com.company.bank.transactions.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person implements Savable {
    private String name;
    private String surname;
    private String PESEL;
    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final List<Transfer> paymentsList = new ArrayList<>();

    public Person() {

    }

    public Person(String name, String surname, String PESEL) {
        for (Person user : BankProvider.getBankProviderInstance().getUsers()) {
            if (user.getPESEL().equals(PESEL))
                throw new IllegalArgumentException("There cannot exists 2 PESEL with one person");
        }
        this.name = name;
        this.surname = surname;
        if (checkPESEL(PESEL)) {
            this.PESEL = PESEL;
        } else
            this.PESEL = null;
    }

    private boolean checkPESEL(String PESEL) {
        if (PESEL.length() != 11)
            throw new IllegalArgumentException("PESEL cannot be accepted!");
        int[] tab = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int value = 0;
        for (int i = 0; i < PESEL.length() - 1; i++) {
            value += (Integer.parseInt(String.valueOf(PESEL.charAt(i))) * tab[i]);
        }
        value %= 10;
        return (10 - value) == Integer.parseInt(String.valueOf(PESEL.charAt(PESEL.length() - 1)));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public List<BankAccount> getAccount() {
        return bankAccountList;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public void addPayment(Transfer transfer) {
        paymentsList.add(transfer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(PESEL, person.PESEL) &&
                Objects.equals(bankAccountList, person.bankAccountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, PESEL);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", \r\nbankAccountList=" + bankAccountList +
                ", paymentsList=" + paymentsList +
                '}';
    }

    @Override
    public String save() {
        return name + " - " + surname + " - " + PESEL;
    }

    @Override
    public void load(String content) {
        String[] parts = content.split(" - ");
        name = parts[0];
        surname = parts[1];
        PESEL = parts[2];
    }
}
